package features;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import helpers.WordFrequency;
import helpers.DocIndex;
import textprocessing.TST;

//Indexing Web Class
public class indexing {
	private TST<List<WordFrequency>> indexedList = new TST<List<WordFrequency>>();
	private HashMap<Integer, DocIndex> mapDocId = new HashMap<Integer, DocIndex>();
	String Word = "";
	public int docId = 0;

	public static String htmlDirPath = "WebDocs/";
	public static String txtDirPath = "WebDocs/Text/";
	public static String txtDirName = "Text/";

	private static indexing indexer;

	// Private constructor
	private indexing() {
	}

	public static indexing getInstance() {
		if (indexer == null) {
			indexer = new indexing();
		}
		return indexer;
	}

	public void startIndexing() throws IOException, InterruptedException {
		List<String> Tokens;
		System.out.println("\r\nIndexing Started...\r\n");

		for (File file : parser.getFilesListWebPage()) {
			String docTitle = file.getName().substring(0, file.getName().length() - 4);
			String parentDir = file.getAbsolutePath().replaceAll(txtDirName, "");
			String docLink = parentDir.substring(0, parentDir.length() - 4) + ".html";
			String docContent = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
			double docLength = docContent.split("[^a-zA-Z0-9'-]").length;

			mapDocId.put(docId, new DocIndex(docTitle, docLink));
			Tokens = parser.parse(docContent);

			Tokens.stream().filter(word1 -> word1.trim().length() > 1 || word1.length() > 1).forEach(word -> {
				Word = word;
				if (null == indexedList.get(Word)) {
					indexedList.put(Word, new ArrayList<WordFrequency>());
					indexedList.get(Word).add(new WordFrequency(docId, 1, docLength));
				} else {
					List<WordFrequency> docList = indexedList.get(Word);
					if (docList.contains(new WordFrequency(docId))) {
						WordFrequency docFreqObj = docList.get(docList.indexOf((new WordFrequency(docId, docLength))));
						docFreqObj.addOccurrence();
					} else {
						WordFrequency newDoc = new WordFrequency(docId, docLength);
						newDoc.addOccurrence();
						docList.add(newDoc);
					}
				}
			});
			docId++;
		}
		System.out.println("\r\nIndexing Finished\r\n");
	}

	public List<DocIndex> tfIdf(String word) {
		List<DocIndex> RankedList = new ArrayList<DocIndex>();
		int totalDocs = parser.getFilesListWebPage().size();
		if (indexedList.get(word) != null) {
			double docListLength = indexedList.get(word).size();
			for (WordFrequency doc : indexedList.get(word)) {
				RankedList.add(new DocIndex(doc.getDocumentId(), mapDocId.get(doc.getDocumentId()).getDocTitle(),
						mapDocId.get(doc.getDocumentId()).getDocLink(),
						doc.getTermFrequency() * Math.log10(totalDocs / docListLength)));
			}
		}
		return RankedList;
	}

	public List<DocIndex> getFilteredList(String query) {
		String[] tokens = query.split(" ");

		List<DocIndex> filteredList = tfIdf(tokens[0]);
		for (int i = 1; i < tokens.length; i++) {
			for (DocIndex doc : tfIdf(tokens[i])) {
				if (filteredList.contains(doc)) {
					filteredList.get(filteredList.indexOf(doc)).addTfIdf(doc.getTfIdf());
				}
			}
		}
		filteredList.sort((c1, c2) -> Double.compare(c2.getTfIdf(), c1.getTfIdf()));
		return filteredList;
	}

	public TST<List<WordFrequency>> getIndexedTerms() {
		return indexedList;
	}
}
