package com.hr.highpriestess.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.badlogic.gdx.backends.gwt.preloader.Preloader;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.*;
import com.hr.highpriestess.HighPriestessMain;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(640, 480);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new HighPriestessMain();
        }

        @Override
        public Preloader.PreloaderCallback getPreloaderCallback () {
                final Panel preloaderPanel = new VerticalPanel();
                preloaderPanel.setStyleName("gdx-preloader");
                final Image logo = new Image(GWT.getHostPageBaseURL() + "assets/GWTbackground.png");
                logo.setStyleName("logo");
                preloaderPanel.add(logo);
                final Panel meterPanel = new SimplePanel();
                meterPanel.setStyleName("gdx-meter");
                meterPanel.addStyleName("red");
                final InlineHTML meter = new InlineHTML();
                final Style meterStyle = meter.getElement().getStyle();
                meterStyle.setWidth(0, Style.Unit.PCT);
                meterStyle.setBackgroundColor("red");
                meterPanel.add(meter);
                preloaderPanel.add(meterPanel);
                getRootPanel().add(preloaderPanel);
                return new Preloader.PreloaderCallback() {

                        @Override
                        public void error (String file) {
                                System.out.println("error: " + file);
                        }

                        @Override
                        public void update (Preloader.PreloaderState state) {
                                meterStyle.setWidth(100f * state.getProgress(), Style.Unit.PCT);
                        }

                };
        }
}