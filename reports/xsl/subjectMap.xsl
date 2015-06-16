<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="text"/>

    <xsl:template match="/map">
        function getMapData() {
            var data = [
                <xsl:apply-templates select="//subject" />
            ];
    
            return data;
        }
    </xsl:template>
        
    <xsl:template match="//subject">
        ['<xsl:value-of select="./@name" />', <xsl:value-of select="./@lat" />, <xsl:value-of select="./@lng" />],
    </xsl:template>
</xsl:stylesheet>
