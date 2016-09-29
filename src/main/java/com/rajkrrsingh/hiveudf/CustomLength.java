package com.rajkrrsingh.hiveudf;


import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.ql.exec.Description;

/**
 * Created by rasingh on 9/28/16.
 *
 *add jar /tmp/SampleCode.jar;
 *
 *CREATE TEMPORARY FUNCTION CustomLength as 'com.rajkrrsingh.hiveudf.CustomLength';
 *
 *describe function CustomLength;
 *OK
 *CustomLength(str) - return a length of string
 *Time taken: 0.039 seconds, Fetched: 1 row(s)
 *
 *select CustomLength('str') from sample_07 limit 1;
 *OK
 *3
 *Time taken: 0.165 seconds, Fetched: 1 row(s)
 *
 */
@Description(
        name = "CustomLength",
        value = "_FUNC_(str) - return a length of string",
        extended = "Example:\n" +
                "  > SELECT CustomLength(str) FROM table;\n" +
                "  3"
)
public class CustomLength extends UDF {

    public String evaluate(String str) {
        if (str == null) {
            return null;
        }
        return String.valueOf(str.length());
    }

}
