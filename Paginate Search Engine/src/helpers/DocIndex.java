package helpers;

/**
 * The Class DocInfo.
 */
public class DocIndex {

	/** The doc title. */
	private String docTitle;

	/** The doc link. */
	private String docLink;
	
	/** The documen id. */
	private int documenId;
	
	/** The tf idf. */
	private double tfIdf;
	
	
	/**
	 * Instantiates a new doc info.
	 *
	 * @param title   the title
	 * @param docLink the doc link
	 */
	public DocIndex(String title, String docLink) {
		this.docTitle = title;
		this.docLink = docLink;
	}

	/**
	 * Gets the doc title.
	 *
	 * @return the doc title
	 */
	public String getDocTitle() {
		return docTitle;
	}

	/**
	 * Gets the doc link.
	 *
	 * @return the doc link
	 */
	public String getDocLink() {
		return docLink;
	}
	/**
	 * Instantiates a new doc rank.
	 *
	 * @param documentId the document id
	 * @param title      the title
	 * @param link       the link
	 * @param tfIdf      the tf idf
	 */
	public DocIndex(int documentId, String title, String link, double tfIdf) {
		this.documenId = documentId;
		this.tfIdf = tfIdf;
		this.docTitle = title;
		this.docLink = link;
	}

	/**
	 * Gets the documen id.
	 *
	 * @return the documen id
	 */
	public int getDocumenId() {
		return documenId;
	}

	/**
	 * Sets the documen id.
	 *
	 * @param documenId the new documen id
	 */
	public void setDocumenId(int documenId) {
		this.documenId = documenId;
	}

	/**
	 * Gets the tf idf.
	 *
	 * @return the tf idf
	 */
	public double getTfIdf() {
		return tfIdf;
	}

	/**
	 * Sets the tf idf.
	 *
	 * @param tfIdf the new tf idf
	 */
	public void setTfIdf(double tfIdf) {
		this.tfIdf = tfIdf;
	}

	/**
	 * Adds the tf idf.
	 *
	 * @param tfIdf the tf idf
	 */
	public void addTfIdf(double tfIdf) {

		this.tfIdf += tfIdf;
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
		result = prime * result + documenId;
		return result;
	}

	/**
	 * Equals.
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
		if (documenId != other.documenId)
			return false;
		return true;
	}
}
