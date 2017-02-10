package com.rajkrrsingh.hiveudf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * Created by rasingh on 9/28/16.
 *
 *add jar /tmp/SampleCode.jar;
 *
 *CREATE TEMPORARY FUNCTION SampleUDF as 'com.rajkrrsingh.hiveudf.SampleUDF';
 *
 *describe function SampleUDF;
 *OK
 *CustomLength(str) - return a length of string
 *Time taken: 0.039 seconds, Fetched: 1 row(s)
 *
 *select SampleUDF('str') from sample_07 limit 1;
 *OK
 *STR
 *Time taken: 0.165 seconds, Fetched: 1 row(s)
 *
 */
@Description(
        name = "SampleUDF",
        value = "_FUNC_(str) - return a uppercase of string",
        extended = "Example:\n" +
                "  > SELECT SampleUDF(str) FROM table;\n" +
                "  3"
)
public class SampleUDF extends UDF {
    public String evaluate(String str) {
        if (str == null) {
            return null;
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return str.toUpperCase();
    }

}

