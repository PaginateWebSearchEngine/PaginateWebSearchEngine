package helpers;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * TSearch Result class
 */
public class Searches {

	/** The query. */
	private String Query;

	/** The time taken. */
	private double time_Taken;

	/** The results. */
	private List<DocIndex> Results;

	/**
	 * Getting the qeury
	 *
	 * @return the query
	 */
	public String getQuery() {
		return Query;
	}

	/**
	 * Setting  the query.
	 *
	 * @param query the new query
	 */
	public void setQuery(String Query) {
		this.Query = Query;
	}
	

	/**
	 * Time taken
	 *
	 * @return the time taken
	 */
	public double getTimeTaken() {
		return time_Taken;
	}

	/**
	 * Sets the time.
	 *
	 * @param timeTaken the new time taken
	 */
	public void setTimeTaken(double time_Taken) {
		this.time_Taken = time_Taken;
	}
	

	/**
	 * Getting the results.
	 *
	 * @return the results
	 */
	public List<DocIndex> getResults() {
		return Results;
	}

	/**
	 * Setting the results.
	 *
	 * @param results the new results
	 */
	public void setResults(List<DocIndex> Results) {
		this.Results = Results;
	}

}
