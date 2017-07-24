package com.merakianalytics.datapipelines.sinks;

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
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import com.merakianalytics.datapipelines.PipelineContext;

@SupportedAnnotationTypes("com.merakianalytics.datapipelines.sinks.PutMany")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class PutManyProcessor extends AbstractProcessor {
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment environment) {
        for(final Element element : environment.getElementsAnnotatedWith(PutMany.class)) {
            final PutMany annotation = element.getAnnotation(PutMany.class);

            if(ElementKind.METHOD != element.getKind()) {
                throw new DataSinkDefinitionException(
                                                      "Must use @PutMany with methods only! Tried to use with " + element.getSimpleName()
                                                      + ", which is not a method.");
            }

            final Set<Modifier> modifiers = element.getModifiers();
            if(!modifiers.contains(Modifier.PUBLIC)) {
                throw new DataSinkDefinitionException(
                                                      "Must use @PutMany with public methods only! Tried to use with " + element.getSimpleName()
                                                      + ", which is not public.");
            }
            if(modifiers.contains(Modifier.STATIC)) {
                throw new DataSinkDefinitionException(
                                                      "Must use @PutMany with non-static methods only! Tried to use with " + element.getSimpleName()
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

            if(TypeKind.VOID != method.getReturnType().getKind()) {
                throw new DataSinkDefinitionException("@PutMany method must have void return! Tried to use with " + element.getSimpleName() + ", which returns "
                                                      + method.getReturnType() + ".");
            }
            if(method.getParameterTypes().size() != 2) {
                throw new DataSinkDefinitionException("@PutMany methods must take 2 arguments: Iterable<T> items, PipelineContext context. Tried to use with "
                                                      + element.getSimpleName() + ", which has a different signature.");
            }

            final TypeElement iterableType = elements.getTypeElement(Iterable.class.getName());
            final TypeMirror[] genericType = new TypeMirror[] {annotatedType};
            final DeclaredType itemsType = types.getDeclaredType(iterableType, genericType);
            final TypeMirror contextType = elements.getTypeElement(PipelineContext.class.getName()).asType();

            if(!types.isAssignable(itemsType, method.getParameterTypes().get(0))) {
                throw new DataSinkDefinitionException(
                                                      "@PutMany method annotated value must be assignable from the generic type of the method's first argument . Tried to use with "
                                                      + element.getSimpleName() + ", which takes " + method.getParameterTypes().get(0) + ".");
            }
            if(!types.isAssignable(method.getParameterTypes().get(1), contextType)) {
                throw new DataSinkDefinitionException("@PutMany method second argument must be assignable from " + contextType + ". Tried to use with "
                                                      + element.getSimpleName() + ", which takes " + method.getParameterTypes().get(1) + ".");
            }
        }

        return true;
    }
}
