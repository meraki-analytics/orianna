package com.merakianalytics.datapipelines.sources;

import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;

@SupportedAnnotationTypes("com.merakianalytics.datapipelines.sources.GetMany")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class GetManyProcessor extends AbstractProcessor {
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment environment) {
        for(final Element element : environment.getElementsAnnotatedWith(GetMany.class)) {
            final GetMany annotation = element.getAnnotation(GetMany.class);

            if(ElementKind.METHOD != element.getKind()) {
                throw new DataSourceDefinitionException(
                                                        "Must use @GetMany with methods only! Tried to use with " + element.getSimpleName()
                                                        + ", which is not a method.");
            }

            final Set<Modifier> modifiers = element.getModifiers();
            if(!modifiers.contains(Modifier.PUBLIC)) {
                throw new DataSourceDefinitionException(
                                                        "Must use @GetMany with public methods only! Tried to use with " + element.getSimpleName()
                                                        + ", which is not public.");
            }
            if(modifiers.contains(Modifier.STATIC)) {
                throw new DataSourceDefinitionException(
                                                        "Must use @GetMany with non-static methods only! Tried to use with " + element.getSimpleName()
                                                        + ", which is static.");
            }

            final ExecutableType method = (ExecutableType)element.asType();
            final Types types = processingEnv.getTypeUtils();
            final Elements elements = processingEnv.getElementUtils();

            // annotation.value() will throw the exception because it doesn't have compiled class information yet, and is of type Class<?>.
            TypeMirror annotatedType = null;
            try {
                annotation.value();
            } catch(final MirroredTypeException e) {
                annotatedType = e.getTypeMirror();
            }

            final TypeElement closeableIteratorType = elements.getTypeElement(CloseableIterator.class.getName());
            final DeclaredType returnType = types.getDeclaredType(closeableIteratorType, annotatedType);

            if(!types.isAssignable(method.getReturnType(), returnType)) {
                throw new DataSourceDefinitionException(
                                                        "@GetMany methods must return a CloseableIterator over a type which is assignable from the annotation value. Tried to use with "
                                                        + element.getSimpleName() + ", which returns " + method.getReturnType() + ".");
            }
            if(method.getParameterTypes().size() != 2) {
                throw new DataSourceDefinitionException(
                                                        "@GetMany methods must take 2 arguments: Map<String, Object> query, PipelineContext context. Tried to use with "
                                                        + element.getSimpleName() + ", which has a different signature.");
            }

            final TypeMirror stringType = elements.getTypeElement(String.class.getName()).asType();
            final TypeMirror objectType = elements.getTypeElement(Object.class.getName()).asType();
            final TypeElement mapType = elements.getTypeElement(Map.class.getName());
            final TypeMirror[] genericTypes = new TypeMirror[] {stringType, objectType};
            final DeclaredType queryType = types.getDeclaredType(mapType, genericTypes);
            final TypeMirror contextType = elements.getTypeElement(PipelineContext.class.getName()).asType();

            if(!types.isAssignable(method.getParameterTypes().get(0), queryType)) {
                throw new DataSourceDefinitionException("@GetMany method first argument must be assignable from " + queryType + ". Tried to use with "
                                                        + element.getSimpleName() + ", which takes " + method.getParameterTypes().get(0) + ".");
            }
            if(!types.isAssignable(method.getParameterTypes().get(1), contextType)) {
                throw new DataSourceDefinitionException("@GetMany method second argument must be assignable from " + contextType + ". Tried to use with "
                                                        + element.getSimpleName() + ", which takes " + method.getParameterTypes().get(1) + ".");
            }
        }

        return true;
    }
}
