package helpers;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class SearchResults.
 */
public class Searches {

	/** The query. */
	private String query;

	/** The time taken. */
	private double timeTaken;

	/** The results. */
	private List<DocIndex> results;

	/**
	 * Gets the query.
	 *
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * Sets the query.
	 *
	 * @param query the new query
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * Gets the results.
	 *
	 * @return the results
	 */
	public List<DocIndex> getResults() {
		return results;
	}

	/**
	 * Sets the results.
	 *
	 * @param results the new results
	 */
	public void setResults(List<DocIndex> results) {
		this.results = results;
	}

	/**
	 * Gets the time taken.
	 *
	 * @return the time taken
	 */
	public double getTimeTaken() {
		return timeTaken;
	}

	/**
	 * Sets the time taken.
	 *
	 * @param timeTaken the new time taken
	 */
	public void setTimeTaken(double timeTaken) {
		this.timeTaken = timeTaken;
	}
}
