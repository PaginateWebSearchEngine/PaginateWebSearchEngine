package features;

public class SpellChecker 
{
	indexing indexer = indexing.getInstance();

	public String spellChecker(String query)
	{
		String[] tokens = query.split(" ");
		String finalQuery = "";

		for (String token : tokens) 
		{
			if (indexer.getIndexedTerms().contains(token)) 
			{
				finalQuery += token + " ";
			} 
			else 
			{
				for (String key : indexer.getIndexedTerms().keys()) 
				{
					if (editDistance(token, key) < 2) 
					{
						finalQuery += key + " ";
						break;
					}
				}
			}

		}
		
		return finalQuery.trim().length() > 0 ? finalQuery : query;
	}

	
//	Calculate the edit distance for the search term 
	public static int editDistance(String word1, String word2) 
	{
		int len1 = word1.length();
		int len2 = word2.length();

		// len1+1, len2+1, because finally return dp[len1][len2]
		int[][] dist = new int[len1 + 1][len2 + 1];

		for (int i = 0; i <= len1; i++) 
		{
			dist[i][0] = i;
		}

		for (int j = 0; j <= len2; j++) 
		{
			dist[0][j] = j;
		}

		// iterate though, and check last char
		for (int i = 0; i < len1; i++) 
		{
			char char1 = word1.charAt(i);
			for (int j = 0; j < len2; j++) 
			{
				char char2 = word2.charAt(j);

				// if last two chars equal
				if (char1 == char2) 
				{
					// update dp value for +1 length
					dist[i + 1][j + 1] = dist[i][j];
				} 
				else 
				{
					int replace = dist[i][j] + 1;
					int insert = dist[i][j + 1] + 1;
					int delete = dist[i + 1][j] + 1;

					int min = replace > insert ? insert : replace;
					min = delete > min ? min : delete;
					dist[i + 1][j + 1] = min;
				}
			}
		}

		return dist[len1][len2];
	}
}
