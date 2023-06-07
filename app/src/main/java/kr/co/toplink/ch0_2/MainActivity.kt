package kr.co.toplink.ch0_2

import android.animation.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import kr.co.toplink.ch0_2.databinding.ActivityMainBinding
import kr.co.toplink.ch0_2.R

class MainActivity : AppCompatActivity() {

    private val TAG = this.javaClass.simpleName
    private lateinit var binding: ActivityMainBinding

    lateinit var star: ImageView
    lateinit var rotateButton: Button
    lateinit var translateButton: Button
    lateinit var scaleButton: Button
    lateinit var sequentialButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        star                = binding.star
        rotateButton        = binding.rotateButton
        translateButton     = binding.translateButton
        scaleButton         = binding.scaleButton
        sequentialButton    = binding.sequentialButton

        title = "에니메이션 기초 Ch0-2"

        star.apply {
                setOnClickListener {
                    val width = star.width
                    val height = star.height
                    val posX = star.x
                    val posY = star.y

                    Toast.makeText(
                        applicationContext,
                        "Start width : $width, height : $height, posX : $posX, posY : $posY",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }

        rotateButton.setOnClickListener {
            rotater()
        }

        translateButton.setOnClickListener {
            translater()
        }

        scaleButton.setOnClickListener {
            scaler()
        }

        sequentialButton.setOnClickListener {
            sequential()
        }
    }

    private fun rotater() {
        val animator =
            AnimatorInflater.loadAnimator(
                applicationContext,
                R.animator.animator_rotation
        ) as Animator

        animator.apply {
            setTarget(star)
            disableViewDuringAnimation(rotateButton)
            start()
        }
    }

    private fun translater() {
        val animator =
            AnimatorInflater.loadAnimator(
                applicationContext,
                R.animator.animator_translate_x
            ) as Animator

        animator.apply {
            setTarget(star)
            disableViewDuringAnimation(translateButton)
            start()
        }
    }

    private fun scaler() {
        val animator = AnimatorInflater.loadAnimator(applicationContext, R.animator.animator_scale)
        animator.apply {
            setTarget(star)
            disableViewDuringAnimation(scaleButton)
            start()
        }
    }

    private fun sequential() {
        val animator =
            AnimatorInflater.loadAnimator(applicationContext, R.animator.animator_sequential)
        animator.apply {
            setTarget(star)
            disableViewDuringAnimation(sequentialButton)
            start()
        }
    }
}


fun Animator.disableViewDuringAnimation(view: View) {

    addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator) {
            view.isEnabled = false
        }

        override fun onAnimationEnd(animation: Animator) {
            view.isEnabled = true
        }
    })
}