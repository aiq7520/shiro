package org.gege.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class UtilsIOTest {
	String filename = "C:\\Users\\lenove1\\Desktop\\8uftp";

	@Test
	public void testIO1() throws MalformedURLException, IOException{
		List<String> lines  = FileUtils.readLines(new File(filename),  "UTF-8");
		System.out.println(lines);
	}
}
