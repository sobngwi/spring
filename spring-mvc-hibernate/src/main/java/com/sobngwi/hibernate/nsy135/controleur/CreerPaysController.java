package com.sobngwi.hibernate.nsy135.controleur;




import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sobngwi.hibernate.nsy135.modele.persistence.Pays;
import com.sobngwi.hibernate.nsy135.service.IServiceHibernate;
import com.sobngwi.hibernate.nsy135.validation.CreationForm;


@Controller
public class CreerPaysController {

	@Inject
    private IServiceHibernate service;

    @RequestMapping(value="/afficherCreationPays", method = RequestMethod.GET)
    public String afficher(final ModelMap pModel) {
        final List<Pays> lListeDesPays = service.listeDesPaysViaHBNHQL();
        pModel.addAttribute("listeDesPays", lListeDesPays);
        if (pModel.get("creation") == null) {
            pModel.addAttribute("creation", new CreationForm());
        }
        return "creation";
    }

    @RequestMapping(value="/creerCreationPays", method = RequestMethod.POST)
    public String creer(@Valid @ModelAttribute(value="creation") final CreationForm pCreation, 
            final BindingResult pBindingResult, final ModelMap pModel) { // Validation des donnees du formulaire. @Valid

        if (!pBindingResult.hasErrors()) {
           
            service.creertPays(pCreation.getCode(), pCreation.getNom(), pCreation.getLangue());
        }
        return afficher(pModel);
    }
}




