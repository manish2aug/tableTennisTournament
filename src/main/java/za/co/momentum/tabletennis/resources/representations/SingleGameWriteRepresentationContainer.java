package za.co.momentum.tabletennis.resources.representations;

import java.util.Collection;

import za.co.momentum.tabletennis.models.SingleGame;

public class SingleGameWriteRepresentationContainer {
	private Collection<SingleGameWriteRepresentation> representations;
	private String captainId;

	@Override
	public String toString() {
		return "SingleGameWriteRepresentationContainer [representations=" + representations + ", captainId=" + captainId
				+ "]";
	}

	public Collection<SingleGame> getCollection(){
		return SingleGameWriteRepresentation.getEntityCollection(this.representations);
	}
	
	public Collection<SingleGameWriteRepresentation> getRepresentations() {
		return representations;
	}

	public void setRepresentations(Collection<SingleGameWriteRepresentation> representations) {
		this.representations = representations;
	}

	public String getCaptainId() {
		return captainId;
	}

	public void setCaptainId(String captainId) {
		this.captainId = captainId;
	}

	
}
