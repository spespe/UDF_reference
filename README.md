# UDF_reference

To use the reference udf:

1) Create a folder containing the jar file in the unix environment;

2) ADD JAR path/reference.jar;

3) LIST JARS; //verify all the jar is there

4) ADD FILE reference_file.txt;

5) CREATE TEMPORARY FUNCTION reference AS 'pkg.UDFReference'; 


Then use the udf inside hive passing the file added to the hadoop distributed cache as second argument.

