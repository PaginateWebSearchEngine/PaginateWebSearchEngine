package features;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.nodes.Document;

public class parser{
	public static String htmlDirPath = "WebDocs/";
	public static String txtDirPath = "WebDocs/Text/";
	public static String txtDirName = "Text/";

	private static Set<String> wordsToBeRemoved = new HashSet<String>(Arrays.asList("a,and,are,an,as,at,be,by,for,from,has,he,is,it,its,of,on,the,that,to,was,were,will,with".split(",")));
	private static List<File> FilesListwebPage = new ArrayList<File>();

	public static List<File> getFilesListWebPage() 
	{
		if (FilesListwebPage.isEmpty()) 
		{
			generateFilesList();
		}
		return FilesListwebPage;
	}

	public static void generateFilesList() 
	{
		try 
		{
			File directory = new File(txtDirPath);
			File[] files = directory.listFiles();
			for (File file : files) {
				if (file.isFile()) {
					FilesListwebPage.add(file);
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in adding file:" + e);
		}
	}
	
	public static List<String> parse(String doc) throws IOException 
	{
		List<String> Listtoken = new ArrayList<String>();
		String[] tokens = doc.toString().split("[^a-zA-Z0-9'-]");
		for (String token : tokens)
			Listtoken.add(token);
		removewordstop(Listtoken);
		return Listtoken;
	}
	
	//Filtering tokens by removing word stops
	public static void removewordstop(List<String> Listinput) 
	{
		Listinput.removeIf(inp -> wordsToBeRemoved.contains(inp));
	}
	
	public static void saveHtml(Document doc) 
	{
		try 
		{
			BufferedWriter html = new BufferedWriter(
					new FileWriter(htmlDirPath + doc.title().replaceAll("[/|?:]", "-") + ".html"));
			html.write(doc.toString());
			html.close();
		} 
		catch (Exception e) 
		{
			System.out.println(
					"Exception in saving html file: " + htmlDirPath + doc.title().replaceAll("[/|?:]", "-") + ".html" + "\r\n" + e);
		}
	}
	public static void saveTxt(Document doc)
	{
		try {
			BufferedWriter text = new BufferedWriter(
					new FileWriter(txtDirPath + doc.title().replaceAll("[/|?:]", "-") + ".txt"));
			text.write(doc.toString());
			text.close();
		}
		catch (Exception e) 
		{
			System.out.println(
					"Exception in saving text file: " + txtDirPath + doc.title().replaceAll("[/|?:]", "-") + ".txt" + "\r\n" + e);
		}
	}
}