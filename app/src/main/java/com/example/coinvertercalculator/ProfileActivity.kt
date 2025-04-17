package com.example.coinvertercalculator

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.coinvertercalculator.app.User
import com.example.coinvertercalculator.helper.UserPreferenceManager
import com.example.coinvertercalculator.helper.min
import com.example.coinvertercalculator.helper.showBasicDialogue
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream


class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val Username = findViewById<EditText>(R.id.Username)
        val Password = findViewById<EditText>(R.id.Password)
        val Email = findViewById<EditText>(R.id.Email)
        val ProfilePic = findViewById<ImageView>(R.id.profile_pic)

        var username = (application as User).username
        var email = (application as User).email
        var password = (application as User).password

        Username.setText(username)
        Password.setText(password)
        Email.setText(email)

        val userPicture = File(applicationContext.filesDir, username + "_profile_picture.png")
        if (userPicture.exists()) {
            ProfilePic.setImageBitmap(BitmapFactory.decodeFile(userPicture.path))
        }

        val userData = getSharedPreferences(username, Context.MODE_PRIVATE)

        val button_back = findViewById<Button>(R.id.button_back)
        button_back.setOnClickListener {
            finish()
        }

        ProfilePic.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.setType("image/*")
            startActivityForResult(photoPickerIntent, 2)
        }

        val save = findViewById<Button>(R.id.save)
        save.setOnClickListener {

            val newProfile = File(applicationContext.filesDir, username + "_temp_profile_picture.png")
            if (newProfile.exists()) {
                if (userPicture.exists()) {
                    userPicture.delete()
                }
                newProfile.renameTo(userPicture)
            }

            username = Username.text.toString()
            email = Email.text.toString()
            password = Password.text.toString()

            (application as User).username = username
            (application as User).password = password
            (application as User).email = email

            //save to device
            val userPrefsManager = UserPreferenceManager(this)
            userPrefsManager.addOrEditUser(username, email, password, false)

            Toast.makeText(this, "Profile Saved", Toast.LENGTH_LONG).show()
        }

        val logout = findViewById<Button>(R.id.logout)
        logout.setOnClickListener {
            //popup
            showBasicDialogue(
                this,
                "Logout?",
                "You will be redirected to login page",
                "Confirm",
                onConfirm = {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            try {
                val imageUri: Uri? = data?.data
                val imageStream = imageUri?.let { contentResolver.openInputStream(it) }
                var selectedImage = BitmapFactory.decodeStream(imageStream)

                val maxDimension = min(selectedImage.width, selectedImage.height)
                var x = 0
                var y = 0
                if (selectedImage.width > maxDimension) {
                    x = (selectedImage.width - maxDimension) / 2
                }
                if (selectedImage.height > maxDimension) {
                    y = (selectedImage.height - maxDimension) / 2
                }

                selectedImage = Bitmap.createBitmap(selectedImage, x, y, maxDimension, maxDimension)

                var username = (application as User).username

                val file = File(applicationContext.filesDir, username + "_temp_profile_picture.png")
                if (file.exists()) {
                    file.delete()
                }

                val out = FileOutputStream(file)
                selectedImage.compress(Bitmap.CompressFormat.PNG, 85, out)
                out.flush()
                out.close()

                findViewById<ImageView>(R.id.profile_pic).setImageBitmap(selectedImage)

            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
            }
        }
    }

}