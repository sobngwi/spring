package com.sobngwi.hibernate.nsy135.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("appStatisticsBean")
public class AppStatisticsImpl implements AppStatistics {
    @Autowired
    private IServiceHibernate serviceHibernate;

    @Override
    public int getTotalContactCount() {
        return serviceHibernate.listeDesFilmsViaHBNHQL().size();
    }
}
