package com.itextpdf.svg.processors.impl;

import com.itextpdf.io.font.FontProgram;
import com.itextpdf.layout.font.FontInfo;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.layout.font.FontSet;
import com.itextpdf.styledxmlparser.css.media.MediaDeviceDescription;
import com.itextpdf.styledxmlparser.resolver.font.BasicFontProvider;
import com.itextpdf.styledxmlparser.resolver.resource.ResourceResolver;
import com.itextpdf.svg.processors.ISvgConverterProperties;

/**
 * Context class with accessors to properties/objects used in processing Svg documents
 */
public class SvgProcessorContext {
    /**
     * The font provider.
     */
    private FontProvider fontProvider;
    /**
     * Temporary set of fonts used in the PDF.
     */
    private FontSet tempFonts;

    private ResourceResolver resourceResolver;
    /**
     * The device description.
     */
    private MediaDeviceDescription deviceDescription;

    /**
     * Instantiates a new {@link SvgProcessorContext} instance.
     *
     * @param converterProperties a {@link ISvgConverterProperties} instance
     */
    public SvgProcessorContext(ISvgConverterProperties converterProperties) {

        deviceDescription = converterProperties.getMediaDeviceDescription();
        if (deviceDescription == null) {
            deviceDescription = MediaDeviceDescription.getDefault();
        }

        fontProvider = converterProperties.getFontProvider();
        if (fontProvider == null) {
            fontProvider = new BasicFontProvider();
        }

        String baseUri = converterProperties.getBaseUri();
        if (baseUri == null) {
            baseUri = "";
        }
        //TODO DEVSIX-2095
        resourceResolver = new ResourceResolver(baseUri);
    }

    /**
     * Gets the font provider.
     *
     * @return the font provider
     */
    public FontProvider getFontProvider() {
        return fontProvider;
    }

    /**
     * Gets the resource resolver.
     *
     * @return the resource resolver
     */
    public ResourceResolver getResourceResolver() {
        return resourceResolver;
    }

    /**
     * Gets the device description.
     *
     * @return the device description
     */
    public MediaDeviceDescription getDeviceDescription() {
        return deviceDescription;
    }

    /**
     * Gets the temporary set of fonts.
     *
     * @return the set of fonts
     */
    public FontSet getTempFonts() {
        return tempFonts;
    }

    /**
     * Add temporary font from @font-face.
     *
     * @param fontProgram the font program
     * @param encoding    the encoding
     * @param alias       the alias
     */
    public void addTemporaryFont(FontProgram fontProgram, String encoding, String alias) {
        if (tempFonts == null) tempFonts = new FontSet();
        tempFonts.addFont(fontProgram, encoding, alias);
    }

    /**
     * Add temporary font from @font-face.
     *
     * @param fontInfo the font info
     * @param alias    the alias
     */
    public void addTemporaryFont(FontInfo fontInfo, String alias) {
        if (tempFonts == null) tempFonts = new FontSet();
        tempFonts.addFont(fontInfo, alias);
    }
}