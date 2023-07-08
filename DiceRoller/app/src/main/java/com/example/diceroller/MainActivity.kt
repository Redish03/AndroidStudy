package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//      모든 Kotlin 시스템에는 main()함수가 있어야 하나, Android 시스템에서는 main()을 호출하는 것이 아니라
//      앱이 처음 열릴 때 MainActivity의 onCreate() 메서드를 호출한다.
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.button)
//      findViewByID 는 레이아웃에서 ()안을 찾는다.
//      R.id.button은 Button의 리소스 아이디로, Button의 고유한 식별자이다.
//      Button 객체 자체가 아니라, rollButton 이라는 변수에 Button 객체 참조를 저장함.
//      Anroid는 자동으로 앱의 리소스를 저장함. ex. Roll버튼에는 리소스 id가 있고, 버튼 텍스트의 문자열에도 리소스 id가 있다.
//      리소스 id의 형식은 R.<type>.<name>이다. (ex. R.string.roll). view ID의 경우, <type>이 id이다. (ex. R.id.button)
//      이 코드는 Button 객체 자체가 아니라, rollButton 이라는 변수에 Button 객체 참조를 저장한다.
        rollButton.setOnClickListener {
            rollDice()
        }
        rollDice()
    }

    private fun rollDice() {
        val dice = Dice(6)
        val diceRoll = dice.roll()

        val diceImage: ImageView = findViewById(R.id.imageView)

        val drawbleResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 ->R.drawable.dice_2
            3 ->R.drawable.dice_3
            4 ->R.drawable.dice_4
            5 ->R.drawable.dice_5
            else ->R.drawable.dice_6
        }
        diceImage.setImageResource(drawbleResource)
        diceImage.contentDescription = diceRoll.toString()
    }
}

class Dice(val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}

/*
    val toast = Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_LONG)
    toast.show()
          rollButton 객체를 만들고, setOnClickListener() 메서드 호출하여 클릭 리스너를 설정함.
          메서드 이름뒤에 괄호가 아닌, 중괄호를 사용. Toast는 사용자에게 뜨는 안드로이드 스튜디오에서 지원하는 간략한 메세지이다.
          Toast : 작은 메시지 박스. Toast.makeText().show를 사용해 메시지 박스를 띄운다.
          makeText에는 3개의 파라미터가 들어간다. (컨텍스트, TEXT, 길이)가 들어간다.
          컨택스트 : 사용할 어플리케이션 활동개체를 의미한다. 보통 안드로이드 앱에서 띄울것이므로 getApplicationContext()가 들어간다.
          TEXT : 출력할 메세지, 길이 : Toast.LENGTH_SHORT : 짧게, Toast.LENGTH_LONG : 길게
          위 두줄의 코드를 Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_SHORT).show() 로 나타낼수 있다.
*/

/*
* val resultTextView : TextView = findViewById(R.id.textView)
            resultTextView.text = "6"
            텍스트는 무조건 큰따옴표 안에 있어야 한다. TextView에는 항상 6이 표시된다.
*/

/*
rollDice()함수를 만들때 선언이 안되어 있다면, Alt + Enter을 눌러서 solution을 찾는다.
 */