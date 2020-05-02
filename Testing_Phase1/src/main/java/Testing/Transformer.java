package Testing;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class Transformer implements ClassFileTransformer {

    private final String package_name;

    public Transformer(String package_name) {
        this.package_name = package_name;
    }

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        if (className.startsWith(package_name)){
            ClassReader c_reader = new ClassReader(classfileBuffer);
            ClassWriter c_writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
            ClassTransformerAndVisitor ca = new ClassTransformerAndVisitor(c_writer, className);
            c_reader.accept(ca, 0);
            return c_writer.toByteArray();
        }
        return classfileBuffer;
    }
}
