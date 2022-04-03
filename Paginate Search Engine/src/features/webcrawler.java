package features;

import java.util.ArrayList;
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
	
	private int MaxDepth = 3;
	
	public void crawl() 
	{	
		String[] urls = { "https://www.uwindsor.ca/",
						"https://beebom.com/microsoft-xbox-series-x-mini-fridges" };
		for (String url : urls) 
		{
			startCrawler(url, 0);
		}
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
				if (depth < MaxDepth) 
				{
					Elements links = doc.select("a[href]");
					for (Element page : links) 
					{
						if (shouldCrawlUrl(page.attr("abs:href"))) 
						{
							System.out.println(ListWebPage.size() + ": " + page.attr("abs:href"));

							startCrawler(page.attr("abs:href"), depth);
							ListCrawled.add(page.attr("abs:href"));
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
	
	public static void main(String[] args)
	{
		new webcrawler().crawl();
	}

}