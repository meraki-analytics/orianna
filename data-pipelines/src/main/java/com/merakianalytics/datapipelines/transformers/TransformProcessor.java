package com.merakianalytics.datapipelines.transformers;

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
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import com.merakianalytics.datapipelines.PipelineContext;

@SupportedAnnotationTypes("com.merakianalytics.datapipelines.transformers.Transform")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class TransformProcessor extends AbstractProcessor {
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment environment) {
        for(final Element element : environment.getElementsAnnotatedWith(Transform.class)) {
            final Transform annotation = element.getAnnotation(Transform.class);

            if(ElementKind.METHOD != element.getKind()) {
                throw new DataTransformerDefinitionException(
                                                             "Must use @Transform with methods only! Tried to use with " + element.getSimpleName()
                                                             + ", which is not a method.");
            }

            final Set<Modifier> modifiers = element.getModifiers();
            if(!modifiers.contains(Modifier.PUBLIC)) {
                throw new DataTransformerDefinitionException(
                                                             "Must use @Transform with public methods only! Tried to use with " + element.getSimpleName()
                                                             + ", which is not public.");
            }
            if(modifiers.contains(Modifier.STATIC)) {
                throw new DataTransformerDefinitionException(
                                                             "Must use @Transform with non-static methods only! Tried to use with " + element.getSimpleName()
                                                             + ", which is static.");
            }

            final ExecutableType method = (ExecutableType)element.asType();
            final Types types = processingEnv.getTypeUtils();
            final Elements elements = processingEnv.getElementUtils();

            // annotation.to() will throw the exception because it doesn't have compiled class information yet, and is of type Class<?>.
            TypeMirror toType = null;
            try {
                annotation.to();
            } catch(final MirroredTypeException e) {
                toType = e.getTypeMirror();
            }

            if(!types.isAssignable(method.getReturnType(), toType)) {
                throw new DataTransformerDefinitionException(
                                                             "@Transform method return type must be assignable from the annotated \"to\" type! Tried to use with "
                                                             + element.getSimpleName()
                                                             + ", which returns " + method.getReturnType() + ".");
            }
            if(method.getParameterTypes().size() != 2) {
                throw new DataTransformerDefinitionException("@Transform methods must take 2 arguments: T item, PipelineContext context. Tried to use with "
                                                             + element.getSimpleName() + ", which has a different signature.");
            }

            // annotation.from() will throw the exception because it doesn't have compiled class information yet, and is of type Class<?>.
            TypeMirror fromType = null;
            try {
                annotation.from();
            } catch(final MirroredTypeException e) {
                fromType = e.getTypeMirror();
            }

            final TypeMirror contextType = elements.getTypeElement(PipelineContext.class.getName()).asType();

            if(!types.isAssignable(method.getParameterTypes().get(0), fromType)) {
                throw new DataTransformerDefinitionException(
                                                             "@Transform method first argument must be assignable from the annotated \"from\" type. Tried to use with "
                                                             + element.getSimpleName()
                                                             + ", which takes " + method.getParameterTypes().get(0) + ".");
            }
            if(!types.isAssignable(method.getParameterTypes().get(1), contextType)) {
                throw new DataTransformerDefinitionException("@Transform method second argument must be assignable from " + contextType + ". Tried to use with "
                                                             + element.getSimpleName() + ", which takes " + method.getParameterTypes().get(1) + ".");
            }
        }

        return true;
    }
}
