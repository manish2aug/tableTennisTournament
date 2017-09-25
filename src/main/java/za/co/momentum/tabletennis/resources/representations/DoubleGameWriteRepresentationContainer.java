package za.co.momentum.tabletennis.resources.representations;

import java.util.Collection;

import za.co.momentum.tabletennis.models.DoubleGame;

public class DoubleGameWriteRepresentationContainer {
	private Collection<DoubleGameWriteRepresentation> representations;
	private String captainId;

	public Collection<DoubleGame> getCollection() {
		return DoubleGameWriteRepresentation.getEntityCollection(this.representations);
	}

	@Override
	public String toString() {
		return "DoubleGameWriteRepresentationContainer [representations=" + representations + ", captainId=" + captainId
				+ "]";
	}

	public Collection<DoubleGameWriteRepresentation> getRepresentations() {
		return representations;
	}

	public void setRepresentations(Collection<DoubleGameWriteRepresentation> representations) {
		this.representations = representations;
	}

	public String getCaptainId() {
		return captainId;
	}

	public void setCaptainId(String captainId) {
		this.captainId = captainId;
	}

}
