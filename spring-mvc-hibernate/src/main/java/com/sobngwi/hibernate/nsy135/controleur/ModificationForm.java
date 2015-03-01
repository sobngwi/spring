package com.sobngwi.hibernate.nsy135.controleur;



import java.util.List;

import javax.validation.Valid;

public class ModificationForm {

    @Valid
    private List<ModificationPays> listeDesPays;

	public List<ModificationPays> getListeDesPays() {
		return listeDesPays;
	}

	public void setListeDesPays(List<ModificationPays> listeDesPays) {
		this.listeDesPays = listeDesPays;
	}

    
}