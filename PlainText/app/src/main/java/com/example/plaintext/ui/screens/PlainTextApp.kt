package com.example.plaintext.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.plaintext.data.model.PasswordInfo
import com.example.plaintext.ui.screens.editList.EditList
import com.example.plaintext.ui.screens.hello.Hello_screen
import com.example.plaintext.ui.screens.list.AddButton
import com.example.plaintext.ui.screens.list.ListItemContent
import com.example.plaintext.ui.screens.login.Login_screen
import com.example.plaintext.ui.screens.login.TopBarComponent
import com.example.plaintext.ui.screens.preferences.SettingsScreen
import com.example.plaintext.ui.viewmodel.ListViewModel
import com.example.plaintext.utils.parcelableType
import kotlin.reflect.typeOf

@Composable
fun PlainTextApp(
    appState: PlainTextAppState = rememberPlainTextAppState()
) {

    NavHost(
        navController = appState.navController,
//        startDestination = Screen.Hello("DevTITANS")
        startDestination = Screen.Login
    ) {

        /*
         * Hello
         */
        composable<Screen.Hello> { backStackEntry ->

            val args =
                backStackEntry.toRoute<Screen.Hello>()

            Hello_screen(args)
        }

        /*
         * Login
         */
        composable<Screen.Login> {

            Login_screen(
                navigateToSettings = appState::navigateToPreferences,
                navigateToList = appState::navigateToList
            )
        }

        /*
         * Preferences
         *
         * Task 3.2
         */
        composable<Screen.Preferences> {

            SettingsScreen(
                navController = appState.navController
            )
        }

        /*
         * List
         *
         * Task 6.2
         */
        composable<Screen.List> {

            val viewModel: ListViewModel =
                hiltViewModel()

            Scaffold(
                topBar = {
                    TopBarComponent(
                        title = "PlainText"
                    )
                },

                floatingActionButton = {

                    AddButton(
                        onClick = {

                            /*
                             * Task 7.1
                             *
                             * Botão +
                             * abre EditList para cadastrar
                             * uma nova senha.
                             */

                            appState.navigateToEditList(
                                password = PasswordInfo(
                                    id = 0,
                                    name = "",
                                    login = "",
                                    password = "",
                                    notes = ""
                                ),
                                title = "Adicionar nova senha"
                            )
                        }
                    )
                }
            ) { paddingValues ->

                ListItemContent(
                    modifier = Modifier.padding(
                        paddingValues
                    ),

                    listState =
                        viewModel.listViewState,

                    /*
                     * Task 7.1
                     *
                     * Clique em um item existente
                     * abre EditList para edição.
                     */
                    navigateToEdit = { password ->

                        appState.navigateToEditList(
                            password = password,
                            title = "Editar Senha"
                        )
                    }
                )
            }
        }

        /*
         * EditList
         *
         * Task 7.1
         */
        composable<Screen.EditList>(
            typeMap = mapOf(
                typeOf<PasswordInfo>() to
                        parcelableType<PasswordInfo>()
            )
        ) { backStackEntry ->

            val args =
                backStackEntry.toRoute<Screen.EditList>()

            val viewModel: ListViewModel =
                hiltViewModel()

            EditList(
                args = args,

                navigateBack =
                    appState::navigateBack,

                savePassword = { password ->

                    viewModel.savePassword(
                        password
                    )

                    appState.navigateBack()
                }
            )
        }
    }
}