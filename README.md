# Testing-Coverage-Tool

Software Testing, Validation and Verification  
Spring 2020  
  
Team  
- Prateek Sarna (pxs180012)
- Shivani Mankotia (sxm180018)
- Nirbhay Sibal (nxs180002)
- Jeel Christian (jsc180001)

Files Included
- Agent.java
- ClassTransformer.java
- ClassTransformVisitor.java
- jUnitListener.java
- MethodTransformVisitor.java

Phase 1  
- Use ASM byte-code manipulation framework [1] to build an automated coverage collection tool that can capture the statement coverage for the program under test. Then, apply your tool to 10 real-world Java projects (>1000 lines of code) with JUnit tests (>50 tests) from GitHub [2] to collect the statement coverage for its JUnit tests. Note that your tool should (1) use Java Agent [3] to perform on-the-fly code instrumentation, (2) be able to store the coverage for each test method in the file system, and (3) be integrated with the Maven build system [4] so that your tool can be triggered by simply typing “mvn test” after changing the pom.xml file of the project under test. More implementation details are shown in the appendix.

Phase 2  
- Further augment the coverage collection tool implemented in Phase-1 to trace more information about program internal states, e.g., tracing accessible field/variable values for the beginning of each method execution. Then, infer the possible single-variable invariants based on the Daikon technique.  
Reference: https://plse.cs.washington.edu/daikon/  
Evaluate your tool to infer invariants for one real-world library project from Phase 1, such as commons-utils or joda-time
