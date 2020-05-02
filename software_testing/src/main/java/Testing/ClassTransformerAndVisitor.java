package Testing;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class ClassTransformerAndVisitor extends ClassVisitor implements Opcodes {

    private String class_name;

    public ClassTransformerAndVisitor(final ClassVisitor cv, final String class_name) {
        super(ASM5, cv);
        this.class_name = class_name;
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        return mv == null ? null : new MethodTransformVisitor(mv, name, class_name);
    }
 
}