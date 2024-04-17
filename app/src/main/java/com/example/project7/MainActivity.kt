package com.example.project7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.InputStream
import javax.xml.parsers.SAXParser
import javax.xml.parsers.SAXParserFactory

class MainActivity : AppCompatActivity() {

    private lateinit var gameView: GameView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var factory : SAXParserFactory =  SAXParserFactory.newInstance()
        var saxParser : SAXParser = factory.newSAXParser()

        var handler : SAXHandler = SAXHandler()
        var iStream : InputStream = resources.openRawResource( R.raw.balloons3 )
        saxParser.parse( iStream, handler )

        var items : ArrayList<Balloon> = handler.getBalloons()
        for ( item in items ) {
            Log.w("MainActivity", item.toString())
        }

        gameView = GameView(this)
        setContentView(gameView)
        gameView.setBalloons(Balloons(items))
    }
}

