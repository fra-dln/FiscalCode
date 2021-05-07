package it.francescodalena.codicefiscaledalena

import android.os.Bundle
//import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import it.francescodalena.codicefiscaledalena.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {
  //  private var TAG= "boh"
    private lateinit var binding: ActivityMainBinding
    private var name = ""
    private var surname = ""
    private var gender = ""
    private var tempCF=""
    private var birthplace = arrayOf("","")
    private var birthday = arrayOf(0,0,0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculate.setOnClickListener {

                this.name = binding.name.text.toString()
                this.surname = binding.surname.text.toString()
                this.birthplace[0] = binding.birthplace.text.toString()
                this.birthplace[1] = binding.birthplace2.text.toString()

                if(binding.male.isChecked)
                    gender="M"
                else if(binding.female.isChecked)
                    gender="F"

                this.birthday[0] = binding.birthday.dayOfMonth
                this.birthday[1] = binding.birthday.month
                this.birthday[2] = binding.birthday.year

                tempCF=calculateSurname(this.surname)+calculateName(this.name)+calculateBirthday(this.birthday,this.gender)+calculateBirthplaceCode(this.birthplace[0],this.birthplace[1])
                tempCF += controlNum(tempCF)
                binding.fiscalCode.text= tempCF
        }
    }

    private fun calculateSurname(surname: String): String {
        var res = ""
        var ctrl = 0
        var givenSurname = surname.toUpperCase(Locale.ROOT)
        givenSurname = givenSurname.replace("\\s".toRegex(), "")
        for (i in givenSurname.indices) {
            if (givenSurname[i] != 'A' && givenSurname[i] != 'E' && givenSurname[i] != 'I' && givenSurname[i] != 'O' && givenSurname[i] != 'U' && givenSurname[i] != 'Y') {
                res += givenSurname[i]
                ctrl++
            }
            if (ctrl == 3) {
                break
            }
        }
        if (res.length < 3) {
            for (i in givenSurname.indices) {
                if (givenSurname[i] == 'A' && givenSurname[i] == 'E' && givenSurname[i] == 'I' && givenSurname[i] == 'O' && givenSurname[i] == 'U' && givenSurname[i] == 'Y') {
                    res += givenSurname[i]
                    ctrl++
                }
                if (ctrl == 3) {
                    break
                }
            }
        }
        while (res.length < 3) {
            res += 'X'
        }
        return res
    }

    private fun calculateName(name: String): String {
        var res = ""
        var ctrl = 0
        var givenName = name.toUpperCase(Locale.ROOT)
        givenName = givenName.replace("\\s".toRegex(), "")
        var consCount = 0
        for (i in givenName.indices) {
            if (givenName[i] != 'A' && givenName[i] != 'E' && givenName[i] != 'I' && givenName[i] != 'O' && givenName[i] != 'U' && givenName[i] != 'Y') {
                consCount++
            }
        }
        if (consCount > 3) {
            for (i in givenName.indices) {
                if (givenName[i] != 'A' && givenName[i] != 'E' && givenName[i] != 'I' && givenName[i] != 'O' && givenName[i] != 'U' && givenName[i] != 'Y') {
                    if (ctrl == 4) {
                        break
                    }
                    ctrl++
                    if (ctrl == 2) {
                        //doNothing
                    } else {
                        res += givenName[i]
                    }
                }
            }
        } else if (consCount == 3) {
            for (i in givenName.indices) {
                if (givenName[i] != 'A' && givenName[i] != 'E' && givenName[i] != 'I' && givenName[i] != 'O' && givenName[i] != 'U' && givenName[i] != 'Y') {
                    if (ctrl == 3) {
                        break
                    }
                    ctrl++
                    res += givenName[i]
                }
            }
        }
        while (res.length < 3) {
            res += 'X'
        }
        return res
    }

    private fun calculateBirthday(birthday: Array<Int>, gender: String): String {
        var res = ""
        var temp = ""
        res += birthday[2].toString().takeLast(2)
        res += when ((birthday[1]+1).toString()) {
            "1" -> "A"
            "2" -> "B"
            "3" -> "C"
            "4" -> "D"
            "5" -> "E"
            "6" -> "H"
            "7" -> "L"
            "8" -> "M"
            "9" -> "P"
            "10" -> "R"
            "11" -> "S"
            "12" -> "T"
            else -> "X"
        }
        if (gender == "M")
            if (birthday[0].toString().length == 1) {
                temp = "0"+temp
                res+=temp
            } else {
                res += birthday[0].toString()
            }
        else
            res += (birthday[0] + 40).toString()

        return res
    }

    private fun controlNum(cf: String): String {
        val res: String
        var cipher = 0

        for (i in cf.indices) {
            if ((i + 1) % 2 == 0) {
                cipher += when (cf[i]) {
                    'A' -> 0
                    '0' -> 0
                    'B' -> 1
                    '1' -> 1
                    'C' -> 2
                    '2' -> 2
                    'D' -> 3
                    '3' -> 3
                    'E' -> 4
                    '4' -> 4
                    'F' -> 5
                    '5' -> 5
                    'G' -> 6
                    '6' -> 6
                    'H' -> 7
                    '7' -> 7
                    'I' -> 8
                    '8' -> 8
                    'J' -> 9
                    '9' -> 9
                    'K' -> 10
                    'L' -> 11
                    'M' -> 12
                    'N' -> 13
                    'O' -> 14
                    'P' -> 15
                    'Q' -> 16
                    'R' -> 17
                    'S' -> 18
                    'T' -> 19
                    'U' -> 20
                    'V' -> 21
                    'W' -> 22
                    'X' -> 23
                    'Y' -> 24
                    'Z' -> 25
                    else -> 0
                }
            } else {
                cipher += when (cf[i]) {
                    'A' -> 1
                    '0' -> 1
                    'B' -> 0
                    '1' -> 0
                    'C' -> 5
                    '2' -> 5
                    'D' -> 7
                    '3' -> 7
                    'E' -> 9
                    '4' -> 9
                    'F' -> 13
                    '5' -> 13
                    'G' -> 15
                    '6' -> 15
                    'H' -> 17
                    '7' -> 17
                    'I' -> 19
                    '8' -> 19
                    'J' -> 21
                    '9' -> 21
                    'K' -> 2
                    'L' -> 4
                    'M' -> 18
                    'N' -> 20
                    'O' -> 11
                    'P' -> 3
                    'Q' -> 6
                    'R' -> 8
                    'S' -> 12
                    'T' -> 14
                    'U' -> 16
                    'V' -> 10
                    'W' -> 22
                    'X' -> 25
                    'Y' -> 24
                    'Z' -> 23
                    else -> 0
                }
            }
        }
        res = when (cipher % 26) {
            0 -> "A"
            1 -> "B"
            2 -> "C"
            3 -> "D"
            4 -> "E"
            5 -> "F"
            6 -> "G"
            7 -> "H"
            8 -> "I"
            9 -> "J"
            10 -> "K"
            11 -> "L"
            12 -> "M"
            13 -> "N"
            14 -> "O"
            15 -> "P"
            16 -> "Q"
            17 -> "R"
            18 -> "S"
            19 -> "T"
            20 -> "U"
            21 -> "V"
            22 -> "W"
            23 -> "X"
            24 -> "Y"
            25 -> "Z"
            else -> "a"
        }
        return res
    }

    private fun calculateBirthplaceCode(city: String, prov: String): String {
        lateinit var temp: List<String>
        val reader = assets.open("countrycode.csv").bufferedReader()
        var res = ""

        reader.useLines { lines ->
            lines.forEach {
                temp = it.split(";")
                if (temp[0].toUpperCase(Locale.ROOT).equals(
                        city.toUpperCase(Locale.ROOT),
                        ignoreCase = true
                    ) && temp[1].toUpperCase(Locale.ROOT)
                        .equals(prov.toUpperCase(Locale.ROOT), ignoreCase = true)
                ) {
                    res = temp[2]
                }
            }
        }
        return res
    }
}



