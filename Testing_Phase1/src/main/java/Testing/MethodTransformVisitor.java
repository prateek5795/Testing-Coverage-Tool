package Testing;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class MethodTransformVisitor extends MethodVisitor implements Opcodes {

	String name;
	int line_num;
	private String class_name;

	public MethodTransformVisitor(final MethodVisitor mv, String name, String class_name) {
		super(ASM5, mv);
		this.name = name;
		this.class_name = class_name;
	}


    @Override
    public void visitLabel(Label l) {
        if (line_num == 0) 
            return;
        String str = class_name;
        mv.visitLdcInsn(str);
        mv.visitLdcInsn(line_num);
        mv.visitMethodInsn(INVOKESTATIC, "Testing/CollectionCoverage", "visitLine", "(Ljava/lang/String;I)V", false);
        super.visitLabel(l);
    }

    @Override
    public void visitLineNumber(int line_num, Label l) {
        if (line_num == 0)
            return;
        this.line_num = line_num;
        String temp = class_name;
        mv.visitLdcInsn(temp);
        mv.visitLdcInsn(line_num);
        mv.visitMethodInsn(INVOKESTATIC, "Testing/CollectionCoverage", "visitLine", "(Ljava/lang/String;I)V", false);
        super.visitLineNumber(line_num, l);
    }

}