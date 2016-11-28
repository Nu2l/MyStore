package com.example.sin.projectone;

import android.os.Bundle;

import com.example.sin.projectone.payment.Main;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;

/**
 * Created by naki_ on 11/25/2016.
 */

public class MainNav extends MaterialNavigationDrawer {

    @Override
    public void init(Bundle savedInstanceState) {
        this.addSection(newSection("Payment", new com.example.sin.projectone.payment.Main()));
        this.addSection(newSection("Receipt", new com.example.sin.projectone.item.Main()));
        this.addSection(newSection("Item", new com.example.sin.projectone.item.Main()));
        this.addSection(newSection("Report", new com.example.sin.projectone.report.Main()));
        this.addSubheader("Account");
        this.addSection(newSection("Profile", new com.example.sin.projectone.profile.Main()));
        this.addDivisor();
        this.addSection(newSection("Help & Feedback", new com.example.sin.projectone.help.Main()));
        this.addSection(newSection("Credit", new com.example.sin.projectone.credit.Main()));

    }
}
