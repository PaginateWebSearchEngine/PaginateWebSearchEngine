package Engine;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;

import features.webcrawler;
import features.indexing;
import features.SpellChecker;
import features.parser;
import helpers.DocIndex;
import helpers.Searches;

public class SearchEngine
{
	static indexing indexer = indexing.getInstance();
	static SpellChecker spellChecker = new SpellChecker();
	
	//Search Word after Correction
	private static Searches getResults(String term) throws IOException 
	{
		Searches results = new Searches();
		
		results.setQuery(term.trim());
		
		double startTime = System.nanoTime();
		results.setResults(indexer.getFilteredList(term));
		results.setTimeTaken((System.nanoTime() - startTime) / 1000000.0);
		
		return results;
	}
	public static void deleteFiles() {
		for (File file : parser.getFilesListWebPage()) 
		{
			Path curntPath = FileSystems.getDefault().getPath("").toAbsolutePath();
			String docTitle = file.getName().substring(0, file.getName().length() - 4);
//			System.out.println(curntPath + "\\WebDocs\\" + docTitle + ".html");
			File htmlFile = new File(curntPath + "\\WebDocs\\" + docTitle + ".html");
			file.delete();
			htmlFile.delete();
		}	
	}
	public static void main(String[] agrs) throws Exception
	{	
		System.out.println("\tPaginate Search Engine\n\t");
		
		//Calling Web Crawler
		webcrawler crawl = new webcrawler();
		crawl.crawl();
		
		System.out.println("\n\nPlease wait while we finish processing...");
		indexer.startIndexing();
		
		//User Input for searching
		Scanner input = new Scanner(System.in);
		
		while(true)
		{
			System.out.print("\nEnter the Search term (or exit) : ");
			String searchTerm = input.nextLine();
			if (searchTerm.equalsIgnoreCase("exit"))
			{
				deleteFiles();
				break;
			}
			
			//Spelling Checking
			String correctedTerm = spellChecker.spellChecker(searchTerm);
			
			//Search Result 
			Searches result = getResults(correctedTerm);
			if (!searchTerm.equalsIgnoreCase(result.getQuery())) 
			{
				System.out.println("\nShowing results for '" + result.getQuery() + "'");
				System.out.println("\nInstead for '"+ searchTerm + "'");
			}
			
			//Number of search found & time taken
			System.out.println("\nAbout " + result.getResults().size() + " results ("+ String.format("%.5f", result.getTimeTaken() / 1000.0) + " seconds)");
			if (result.getResults().size() > 15)
			{
				System.out.println("\nTop 10 results...");
			}
			
			//Display Top 10 Results
			int count = 1;
			int partition = 11;
			int additionalResults = 10;
			for (DocIndex res : result.getResults()) 
			{	
				System.out.println("[" +count + "]" + "\t" + "Found in file: " + res.getDocTitle());
				System.out.println("\tat " + res.getDocLink());
//				System.out.println("\tTFIDF: " + res.getTfIdf());
				System.out.println();
				
				if (++count >= partition)
				{
					System.out.print("More "+ additionalResults + " results... (y/n): ");
					searchTerm = input.nextLine();
					
					if(searchTerm.equalsIgnoreCase("n"))
					{
						break;
					}
					else
					{
						partition += additionalResults;
					}
				}
			}
			
		}
		
		input.close();
	}
}
