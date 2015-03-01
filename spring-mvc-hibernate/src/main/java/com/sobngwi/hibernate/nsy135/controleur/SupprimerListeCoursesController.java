package com.sobngwi.hibernate.nsy135.controleur;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sobngwi.hibernate.nsy135.modele.persistence.Pays;
import com.sobngwi.hibernate.nsy135.service.IRegisterMbean;
import com.sobngwi.hibernate.nsy135.service.IServiceHibernate;



@Controller
public class SupprimerListeCoursesController {

	 @Inject
	    private IServiceHibernate service;
	 @Inject 
	 private IRegisterMbean registerMbean;

    @RequestMapping(value="/afficherSuppressionListePays", method = RequestMethod.GET)
    public String afficher(final ModelMap pModel) {
    	registerMbean.registerHibernateMBeans();
        final List<Pays> lListeDesPays = service.listeDesPaysViaHBNHQL();
        pModel.addAttribute("listeDesPays", lListeDesPays);
        return "suppression";
    }

    @RequestMapping(value="/supprimerSuppressionListePays", method = RequestMethod.GET)
    public String supprimer(@RequestParam(value="code") final String pcodePays, final ModelMap pModel) throws Exception {
    	
        service.supprimerPays(pcodePays);
        return afficher(pModel);
    }
}