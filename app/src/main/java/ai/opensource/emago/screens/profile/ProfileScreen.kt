package ai.opensource.emago.screens.profile


//fun ProfileScreen2(navController: NavController, dvm: DBViewModel= hiltViewModel()) {
//    val inProgress = dvm.inProcess.value
//    if (inProgress) {
//        CommonProgressBar()
//    } else {
//        val userData = dvm.userData.value
//        var name by rememberSaveable {
//            mutableStateOf(userData?.name?:"")
//        }
//        var number by rememberSaveable {
//            mutableStateOf(userData?.number?:"")
//        }
//        Column {
//            ProfileContent(
//                modifier = Modifier
//                    .weight(1f)
//                    .verticalScroll(rememberScrollState())
//                    .padding(8.dp),
//                vm=vm,
//                name = name,
//                number = number,
//                onNameChange = {name=it},
//                onNumberChange = {number=it},
//                onSave = {
//                    vm.createOrUpdateProfile(
//                        name, number
//                    )
//                },
//                onBack = {
//                    navigateTo(navController, DestinationScreen.ChatList.route)
//                },
//                onLogout = {
//                    vm.logout()
//                    navController.navigate("first"){
//                        popUpTo("first"){
//                            inclusive = true
//                        }
//                    }
//                },
//            )
//        }
//    }
//}
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ProfileContent(
//    modifier: Modifier,
//    name: String,
//    number: String,
//    onNameChange: (String) -> Unit,
//    onNumberChange: (String) -> Unit,
//    onBack: () -> Unit,
//    onSave: () -> Unit,
//    onLogout: () -> Unit,
//    dvm: DBViewModel= hiltViewModel()
//) {
//    val imageUrl = vm.userData.value?.imageUrl
//
//    Column (){
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(4.dp),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Text(text = "Back", Modifier.clickable {
//                onBack.invoke()
//            })
//            Text(text = "Save",
//                Modifier.clickable {
//                    onSave.invoke()
//                })
//        }
//
//
//        CommonDivider()
//        ProfileImage(imageUrl = imageUrl, vm = vm)
//
//        CommonDivider()
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(4.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(text = "Name", modifier = Modifier.width(100.dp))
//
//            TextField(
//                value = name, onValueChange = onNameChange,
//                colors = TextFieldDefaults.textFieldColors(
//                    focusedTextColor = Color.Black,
//                    containerColor = Color.Transparent,
//                )
//            )
//        }
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(4.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(text = "Number", modifier = Modifier.width(100.dp))
//            TextField(
//                value = number, onValueChange = onNumberChange,
//                colors = TextFieldDefaults.textFieldColors(
//                    focusedTextColor = Color.Black,
//                    containerColor = Color.Transparent,
//                )
//            )
//        }
//        CommonDivider()
//        Row (modifier= Modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//            horizontalArrangement = Arrangement.Center){
//            Text(text = "LogOut", modifier = Modifier.clickable {
//                onLogout.invoke()
//            })
//
//        }
//    }
//}
//


//fun ProfileImage(imageUrl: String?, vm: EMAGOViewModel) {
//
//    val launcher =
//        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
//            uri?.let {
//                vm.uploadProfileImage(uri)
//            }
//        }
//
//    Box(modifier = Modifier.height(intrinsicSize = IntrinsicSize.Min))
//    {
//        Column(
//            modifier = Modifier
//                .padding(8.dp)
//                .fillMaxWidth()
//                .clickable {
//                    launcher.launch("image/*")
//                },
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Card(
//                shape = CircleShape,
//                modifier = Modifier
//                    .padding(8.dp)
//                    .size(100.dp)
//            ) {
//                CommonImage(data = imageUrl)
//
//                }
//            }
//            Text(text = "Change Profile Picture")
//        }
//
//        if (vm.inProcess.value) {
//            CommonProgressBar()
//        }
//    }
