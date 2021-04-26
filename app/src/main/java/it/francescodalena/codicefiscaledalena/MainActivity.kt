package it.francescodalena.codicefiscaledalena

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import it.francescodalena.codicefiscaledalena.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var name=""
    private var surname=""
    private var gender=""
    private var birthplace = arrayOf("","")
    private var birthday = intArrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setVars()
    }

    private fun setVars(){

        binding.name.doAfterTextChanged {
            this.name = binding.name.text.toString()

        }
        binding.surname.doAfterTextChanged {
            this.surname = binding.surname.text.toString()

        }
        binding.birthplace.doAfterTextChanged {
            this.birthplace[0] = binding.birthplace.text.toString()

        }
        binding.birthplace2.doAfterTextChanged {
            this.birthplace[1] = binding.birthplace2.text.toString()

        }
        gender = binding.genderRadio.checkedRadioButtonId.toString()
        binding.genderRadio.setOnCheckedChangeListener { _, checkedId ->
            this.gender = checkedId.toString()
        }
        binding.birthday.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
            birthday[0] = dayOfMonth
            birthday[1] = monthOfYear
            birthday[2] = year
        }
    }


}


