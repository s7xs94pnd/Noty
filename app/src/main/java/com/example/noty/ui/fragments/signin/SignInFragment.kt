import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.noty.R
import com.example.noty.databinding.FragmentSignInBinding
import com.example.noty.utils.PreferenceHelper
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSingInClient: GoogleSignInClient

    private val signInLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    firebaseAuthWithGoogle(account?.idToken)
                } catch (e: ApiException) {
                    updateUI(null)
                }
            }
        }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            findNavController().navigate(R.id.action_signInFragment_to_homeNotyFragment)
        } else
            Toast.makeText(requireContext(), "аутентификация не прошла", Toast.LENGTH_SHORT).show()
    }

    // TODO: дальше изучаем
    private fun firebaseAuthWithGoogle(idToken: String?) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                updateUI(user)
                val sharedPreferences = PreferenceHelper()
                sharedPreferences.helper(requireContext())
                sharedPreferences.isSignedIn = true
            } else {
                updateUI(null)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        auth = Firebase.auth
        /*Firebase.auth — это объект, предоставляемый библиотекой Firebase для работы с аутентификацией. Этот объект позволяет:
	•	Регистрировать пользователей.
	•	Входить в систему.
	•	Выходить из системы.
	•	Проверять, вошел ли пользователь.
	•	Получать информацию о текущем пользователе.

	       auth = Firebase.auth
        Эта строка:
	•	Создает переменную auth, которая хранит ссылку на объект аутентификации.
	•	С помощью этой переменной ты будешь вызывать функции Firebase Authentication.*/

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_sign_in_id))
            .requestEmail()
            .build()
        googleSingInClient = GoogleSignIn.getClient(requireContext(), gso)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.btnSignIn.setOnClickListener {
            signInLauncher.launch(googleSingInClient.signInIntent)
        }
    }
}


/*	1.	val startActivityForResult = ...
Здесь вы создаёте переменную startActivityForResult. В ней регистрируется обработчик результата для запуска другой Activity.
Эта переменная отвечает за:
	•	Запуск новой Activity.
	•	Обработку результата, который возвращается после её завершения.
Это подготовка к сценарию “открыть другую Activity и получить её результат”.*/

/*2.	registerForActivityResult(ActivityResultContracts.StartActivityForResult())
	•	Метод registerForActivityResult регистрирует обработчик результата.
	•	ActivityResultContracts.StartActivityForResult() указывает, что:
	•	Вы будете запускать другую Activity.
	•	Результат её завершения будет возвращён в обработчик в виде объекта ActivityResult.*/

/*	3.	{ result ->
После закрытия другой Activity результат работы передаётся в эту лямбда-функцию через переменную result.
	•	result — это объект класса ActivityResult, который содержит:
	•	resultCode — код завершения (например, RESULT_OK или RESULT_CANCELED).
	•	data — объект Intent, содержащий данные, которые вернула Activity.*/

/*	4.	if (result.resultCode == Activity.RESULT_OK)
	•	Здесь проверяется код результата.
	•	RESULT_OK — это стандартный код завершения, который указывает, что другая Activity завершилась успешно.
	•	Если код результата другой (например, RESULT_CANCELED),
	 это означает, что задача не была выполнена (пользователь нажал «Назад» или произошла ошибка).*/