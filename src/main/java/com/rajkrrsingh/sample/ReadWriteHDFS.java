package com.rajkrrsingh.sample;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.security.UserGroupInformation;

public class ReadWriteHDFS
{
    public static void main(String args[]) throws Exception {
        byte buf[] = new byte[ 65*1024];

        if (args.length <= 3) {
            System.out.println("usage: java -cp SampleCode-1.0-SNAPSHOT.jar com.rajkrrsingh.sample.ReadWriteHDFS <user_prinicpal> <keytab_path> <dir>");
            return;
        }

        String dirname = args[2];

        Configuration conf = new Configuration();
        conf.set("hadoop.security.authentication","Kerberos");
        conf.set("fs.default.name", "hdfs://rksnode.hdp.local:8020");
        conf.set("hadoop.security.authentication","Kerberos");
        UserGroupInformation.setConfiguration(conf);
        UserGroupInformation ugi = UserGroupInformation.loginUserFromKeytabAndReturnUGI(args[0], args[1]);
        FileSystem fs = FileSystem.get(conf);

        Path testDir = new Path(dirname+"/testdir");
        boolean rc = fs.mkdirs( testDir);
        if (!rc) {
            System.out.println("unable to create directory with path " + testDir);
            return;
        }
        System.out.println("created a new directory on path "+testDir);
        Path testfile = new Path( testDir + "/testfile.txt");
        System.out.println("now going to create a file inside the directory");

        FSDataOutputStream fos = fs.create( testfile,true,512,(short) 1, (long)(64*1024*1024));
        fos.write(buf);
        fos.close();

        System.out.println( "reading a recently created file : " + testfile);
        FSDataInputStream istr = fs.open( testfile);
        int bb = istr.readInt();
        istr.close();
        System.out.println( "complete read : DONE");
    }
}
