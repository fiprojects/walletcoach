<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : monthReport.xsl
    Created on : Sobota, 2015, júna 13, 2:55
    Author     : Maros
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/report">
        <html>
            <head>
                <title>Month report</title>
            </head>
            <body>
                <h1>Report for <xsl:value-of select="/report/@month"/>/<xsl:value-of select="/report/@year"/></h1>                
                <table>
                    <tr>
                        <td><b>Summary</b></td>
                    </tr>
                    <tr>
                        <td>Income</td>
                        <td>
                            <xsl:if test="/report/summary/income != ''">
                                <xsl:value-of select="/report/summary/income"/>
                            </xsl:if>
                            <xsl:if test="/report/summary/income = ''">
                                0
                            </xsl:if>
                        </td>
                    </tr>
                    <tr>
                        <td>Expense</td>
                        <td>
                            <xsl:if test="/report/summary/expense != ''">
                                <xsl:value-of select="/report/summary/expense"/>
                            </xsl:if>
                            <xsl:if test="/report/summary/expense = ''">
                                0
                            </xsl:if>
                        </td>
                    </tr>
                    <tr>
                        <td>Total</td>
                        <td>
                            <xsl:if test="/report/summary/total != ''">
                                <xsl:value-of select="/report/summary/total"/>
                            </xsl:if>
                            <xsl:if test="/report/summary/total = ''">
                                0
                            </xsl:if>
                        </td>
                    </tr>
                </table>
                <br/>
                <table>
                    <tr>
                        <td colspan="4">
                            <b>Report by Category</b>
                        </td>
                    </tr>
                    <tr>
                        <td>Category name</td>
                        <td>Income</td>
                        <td>Expense</td>
                        <td>Total</td>
                    </tr>
                    <xsl:apply-templates select="//category"/>
                </table>
                <br/>
                
                <table>
                    <tr>
                        <td colspan="4">
                            <b>Report by Subjects</b>
                        </td>
                    </tr>
                    <tr>
                        <td>Subject name</td>
                        <td>Income</td>
                        <td>Expense</td>
                        <td>Total</td>
                    </tr>
                    <xsl:apply-templates select="//subject"/>
                </table>
              
            </body>
        </html>
    </xsl:template>
    
    <xsl:template match="//category">
        <tr>
            <td><xsl:value-of select="./@name"/></td>
            <td><xsl:value-of select="./@income"/></td>
            <td><xsl:value-of select="./@expense"/></td>
            <td><xsl:value-of select="./@total"/></td>
        </tr>
    </xsl:template>
    
    <xsl:template match="//subject">
        <tr>
            <td><xsl:value-of select="./@name"/></td>
            <td><xsl:value-of select="./@income"/></td>
            <td><xsl:value-of select="./@expense"/></td>
            <td><xsl:value-of select="./@total"/></td>
        </tr>
    </xsl:template>

</xsl:stylesheet>
