package com.sobngwi.hibernate.nsy135.controleur;


import java.util.LinkedList;
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



@Controller
public class ModifierListePaysController {

	@Inject
    private IServiceHibernate service;

    @RequestMapping(value="/afficherModificationListePays", method = RequestMethod.GET)
    public String afficher(final ModelMap pModel) {
        if (pModel.get("modification") == null) {
            final List<Pays> listeDesPays = service.listeDesPaysViaHBNHQL();
            final ModificationForm lModificationForm = new ModificationForm();
            final List<ModificationPays> lListe = new LinkedList<ModificationPays>();
            for (final Pays lPays : listeDesPays) {
                final ModificationPays lModificationPays = new ModificationPays();
                lModificationPays.setCode(lPays.getCode());
                lModificationPays.setNom(lPays.getNom());
                lModificationPays.setLangue(lPays.getLangue());
                lListe.add(lModificationPays);
            }
            lModificationForm.setListeDesPays(lListe);
            pModel.addAttribute("modification", lModificationForm);
        }
        return "modification";
    }

    @RequestMapping(value="/modifierModificationListePays", method = RequestMethod.POST)
    public String modifier(@Valid @ModelAttribute(value="modification") final ModificationForm pModification, 
            final BindingResult pBindingResult, final ModelMap pModel) {

       // if (!pBindingResult.hasErrors()) {
            final List<Pays> lListeDesPays = new LinkedList<Pays>();
            for (final ModificationPays lModificationPays : pModification.getListeDesPays()){
                final Pays lPays = new Pays();
                lPays.setCode(lModificationPays.getCode());
                final String lnom = lModificationPays.getNom();
                final String lLangue = lModificationPays.getLangue();
                lPays.setNom(lnom);
                lPays.setLangue(lLangue);
                lListeDesPays.add(lPays);
          //  }
           
            service.modifierPays(lListeDesPays);
        }

        return afficher(pModel);
    }
}
