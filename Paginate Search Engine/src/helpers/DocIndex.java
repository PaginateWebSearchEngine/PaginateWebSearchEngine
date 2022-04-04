package helpers;

/**
 * The Class DocInfo.
 */
public class DocIndex {

	/** String for doc title */
	private String doc_Title;

	/** String for The doc link. */
	private String doc_Link;
	
	/** The document id. */
	private int doc_Id;
	
	/** The tf idf. */
	private double tf_Idf;
	/**
	 * Gets the document id.
	 *
	 * @return the document id
	 */
	public int getDocumenId() {
		return doc_Id;
	}
	
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + doc_Id;
		return result;
	}

	/**
	 *Equals to check
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocIndex other = (DocIndex) obj;
		if (doc_Id != other.doc_Id)
			return false;
		return true;
	}
	

	/**
	 * Sets the documen id.
	 *
	 * @param documenId the new documen id
	 */
	public void setDocumenId(int documenId) {
		this.doc_Id = documenId;
	}

	
	/**
	 * Instantiating the new Doc ID
	 *
	 * @param title   the title
	 * @param docLink the doc link
	 */
	public DocIndex(String title, String docLink) {
		this.doc_Title = title;
		this.doc_Link = docLink;
	}

	/**
	 * Gets the doc title.
	 *
	 * @return the doc title
	 */
	public String getDocTitle() {
		return doc_Title;
	}

	/**
	 * Gets the doc link.
	 *
	 * @return the doc link
	 */
	public String getDocLink() {
		return doc_Link;
	}
	/**
	 * Instantiates a new doc rank.
	 *
	 * @param documentId the document id
	 * @param title      the title
	 * @param link       the link
	 * @param tfIdf      the tf idf
	 */
	public DocIndex(int docId, String title, String link, double tfIdf) {
		this.doc_Id = docId;
		this.tf_Idf = tfIdf;
		this.doc_Title = title;
		this.doc_Link = link;
	}


	/**
	 * Gets the tf idf.
	 *
	 * @return the tf idf
	 */
	public double getTfIdf() {
		return tf_Idf;
	}

	/**
	 * Sets the tf idf.
	 *
	 * @param tfIdf the new tf idf
	 */
	public void setTfIdf(double tfIdf) {
		this.tf_Idf = tfIdf;
	}

	/**
	 * Adds the tf idf.
	 *
	 * @param tfIdf the tf idf
	 */
	public void addTfIdf(double tfIdf) {

		this.tf_Idf += tfIdf;
	}

	
}
