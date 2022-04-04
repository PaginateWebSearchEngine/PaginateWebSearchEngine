package helpers;

/**
 * The Class DocFrequency.
 */
public class WordFrequency {

	/** The document id for Docs */
	private int doc_Id;

	/** The frequency of appearance*/
	private double frequency;

	/** The term frequency of appearance. */
	private double term_Frequency;

	/** The doc length. */
	private double doc_Length;

	/** The tfidf. */
	private double tf_idf;

	/**
	 * new doc frequency is instantiated
	 *
	 * @param documentId the document id
	 * @param frequency  the frequency
	 * @param docLength  the doc length
	 */
	public WordFrequency(int DocumentId, double Frequency, double docLength) {
		this.doc_Id = DocumentId;
		this.frequency = Frequency;
		this.doc_Length = docLength;
		this.term_Frequency = this.frequency / this.doc_Length;
	}

	/**
	 * without doc_frequency
	 *
	 * @param documentId the document id
	 * @param docLength  the doc length
	 */
	public WordFrequency(int documentId, double docLength) {
		this.doc_Id = documentId;
		this.frequency = 0;
		this.docL_ength = docLength;
		this.term_Frequency = this.frequency / this.doc_Length;
	}

	/**
	 * 
	 *
	 * @param documentId the document id
	 */
	public WordFrequency(int documentId) {
		this.doc_Id = documentId;
		this.frequency = 0;
	}

	/**
	 * Hash code.
	 *
	 * @return result of the search result frequency
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + doc_Id;
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
		WordFrequency other = (WordFrequency) obj;
		if (doc_Id != other.doc_Id)
			return false;
		return true;
	}

	/**
	 * Getting document ID
	 *
	 * @return the document id
	 */
	public int getDocumentId() {
		return doc_Id;
	}

	/**
	 * Gets the frequency.
	 *
	 * @return the frequency
	 */
	public double getFrequency() {
		return frequency;
	}

	/**
	 * Adds the occurrence.
	 */
	public void addOccurrence() {
		this.frequency++;
		this.term_Frequency = this.frequency / this.doc_Length;
	}

	/**
	 * Adds the occurrence.
	 *
	 * @param noOfOccurences the no of occurrences
	 */
	public void addOccurence(double noOfOccurences) {
		this.frequency += noOfOccurences;
		this.term_Frequency = this.frequency / this.doc_Length;
	}

	/**
	 * Gets the term frequency.
	 *
	 * @return the term frequency
	 */
	public double getTermFrequency() {
		return this.term_Frequency;
	}

	/**
	 * Gets the tfidf.
	 *
	 * @return the tfidf
	 */
	public double getTfidf() {
		return tf_idf;
	}

	/**
	 * Sets the tfidf.
	 *
	 * @param tfidf the new tfidf
	 */
	public void setTfidf(double tfidf) {
		this.tf_idf = tfidf;
	}
}

