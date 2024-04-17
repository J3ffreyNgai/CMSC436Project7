package com.example.project7

import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler

class SAXHandler() : DefaultHandler() {

    private var listOfBallon : ArrayList<Balloon> = ArrayList<Balloon>()
    private var currentX = 0
    private var currentY = 0
    private var currentRadius = 0
    private var currentBallon: Balloon? = null
    private var currentElement : String = ""
    private var validText : Boolean = false

    override fun startElement(uri: String?, localName: String?, qName: String?, attributes: Attributes?) {
        super.startElement(uri, localName, qName, attributes)
        validText = true
        if(qName != null) {
            currentElement = qName
            if(currentElement.equals("balloon"))
                currentBallon = Balloon(0,0,0)
        }
    }

    override fun endElement(uri: String?, localName: String?, qName: String?) {
        super.endElement(uri, localName, qName)
        validText = false

        when (qName) {
            "x" -> currentX = currentElement!!.toInt()
            "y" -> currentY = currentElement!!.toInt()
            "radius" -> currentRadius = currentElement!!.toInt()
            "balloon" -> listOfBallon.add(Balloon(currentX,currentY, currentRadius))
        }
    }

    override fun characters(ch: CharArray?, start: Int, length: Int) {
        super.characters(ch, start, length)
        if (ch != null) {
            if( validText && currentBallon != null ) {
                currentElement = String(ch!!, start, length)
                validText = false
            }

        }
    }

    fun getBalloons( ) : ArrayList<Balloon> {
        return listOfBallon
    }
}