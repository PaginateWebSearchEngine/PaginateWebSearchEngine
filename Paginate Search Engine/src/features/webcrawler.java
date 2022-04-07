package features;

import java.util.ArrayList;
import java.util.Scanner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class webcrawler{
	
	private Set<String> ListCrawled = new HashSet<String>();
	private List<Document> ListWebPage = new ArrayList<Document>();
	public static int PagesToCrawl = 10;
	private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	
	public List<Document> getListWebPage() 
	{
		return ListWebPage;
	}
	
	public void setListWebPage(List<Document> ListwebPage) 
	{
		this.ListWebPage = ListwebPage;
	}
	
	private int MaxDepth = 1;
	Scanner sc = new Scanner(System.in);
	int num;
	
	public void crawl() 
	{	
		System.out.println("Enter URL to Crawl: ");
		String urls = sc.nextLine();
		
		System.out.println("Enter Number of URLS to Crawl: ");
		num = sc.nextInt();
//		String[] urls = { "https://www.uwindsor.ca/",
//						"https://beebom.com/microsoft-xbox-series-x-mini-fridges" };
//		for (String url : urls) 
//		{
			startCrawler(urls, 0);
//		}
	}
	
	public void startCrawler(String url, int depth) 
	{
		if (depth <= MaxDepth) 
		{
			try 
			{
				Document doc = Jsoup.connect(url).ignoreHttpErrors(true).userAgent(USER_AGENT).get();
				parser.saveHtml(doc);
				parser.saveTxt(doc);
				ListWebPage.add(doc);
				depth++;
				if (depth <= MaxDepth) 
				{
					Elements links = doc.select("a[href]");
					int linklimit = 1;
					for (Element page : links) 
					{
						if(linklimit > num) {
							break;
						}
						if (shouldCrawlUrl(page.attr("abs:href"))) 
						{
							System.out.println(ListWebPage.size() + ": " + page.attr("abs:href"));
//							System.out.println(linklimit);
//							System.out.println(depth);
							startCrawler(page.attr("abs:href"), depth);
							ListCrawled.add(page.attr("abs:href"));
							linklimit++;
						}
						
					}
				}
			} 
			catch (Exception e) 
			{
				System.out.println("Error:" + url + "\r\n" + e);
			}
		}
	}
	
	private boolean shouldCrawlUrl(String nextUrl) 
	{
		return (this.ListCrawled.contains(nextUrl) || 
				nextUrl.startsWith("javascript:") ||
				nextUrl.contains("mailto:") ||
				nextUrl.contains("#") || 
				nextUrl.contains("?") ||
				nextUrl.endsWith(".swf") ||
				nextUrl.endsWith(".pdf") ||
				nextUrl.endsWith(".png") ||
				nextUrl.endsWith(".jpg") ||
				nextUrl.endsWith(".jpeg") ||
				nextUrl.endsWith(".gif") ?
						false : true
				);
	}
	
	public static void main(String[] args)
	{
		new webcrawler().crawl();
	}

}