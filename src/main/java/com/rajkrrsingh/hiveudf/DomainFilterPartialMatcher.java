package com.rajkrrsingh.hiveudf;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.ql.metadata.HiveException;

public class DomainFilterPartialMatcher extends UDF {

    private ArrayList<String> domainsToFilterList;

    public String evaluate(String sourceURL, String domainFilterFile) throws HiveException {
        if (sourceURL == null || sourceURL.equals(""))
        	return "NA";
        //System.out.println("Source URL: "+sourceURL);
    	if (domainsToFilterList == null || domainsToFilterList.size() == 0) {
    		
        	domainsToFilterList = new ArrayList<String>();
        	//load domain filter URLs 
        	BufferedReader lineReader = null;
            try {
            	lineReader = new BufferedReader(new FileReader(domainFilterFile));

                String line = null;
                while ((line = lineReader.readLine()) != null) {
                	if (line != null && !line.equals(""))
                    domainsToFilterList.add(line);
                }
                System.out.println("domain filter list size: "+domainsToFilterList.size());
                lineReader.close();
            } catch (FileNotFoundException ex) {            	
            	System.err.println("File Not Found ecxeption while loading domain filters into list. ");
            	throw new HiveException(domainFilterFile + " doesn't exist");               
            } catch (IOException ex) {
            	System.err.println("IO ecxeption while loading domain filters into list. ");
                throw new HiveException("Loading domain filter file " + domainFilterFile + " failed, please check format.");
            }
        }

        if (domainsToFilterList != null && domainsToFilterList.size() > 0) {
        	System.err.println("List contains no domain filter URLs for partital match check");
        	for(int i= 0; i < domainsToFilterList.size(); i++) {
        		String domainToFilter = domainsToFilterList.get(i);
        		if (domainToFilter != null &&  !domainToFilter.equals("")) {
        			if (sourceURL.indexOf(domainToFilter) != -1) {
        				//System.out.println("Found domain filter match["+domainToFilter+" for source URL "+sourceURL); 
        				return sourceURL;
        			}
        		}
        	}

        
        }
        //System.out.println("No full/partial filter domain match found for source url "+sourceURL);
        return "NA";
    }
}