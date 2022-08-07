package moe.bitt.filebrowser.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import moe.bitt.filebrowser.R
import moe.bitt.filebrowser.databinding.FragmentSignInBinding

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var viewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[SignInViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.signInState.collect {
                it?.also { result ->
                    if (result) {
                        findNavController().navigate(R.id.mainFragment)
                    } else {
                        Snackbar.make(binding.root, "Ошибка авторизации", Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }

        binding.submitButton.setOnClickListener {
            viewModel.signIn(
                url = binding.serverTextField.text.toString(),
                username = binding.usernameTextField.text.toString(),
                password = binding.passwordTextField.text.toString()
            )
        }

        return binding.root
    }

}