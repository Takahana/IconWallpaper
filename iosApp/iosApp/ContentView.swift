import SwiftUI
import iosExport

struct ContentView: View {
	let greet = Greeting().greeting()
  let uiLogic = WelcomeUiLogicModuleKt.createWelcomeUiLogic()

	var body: some View {
		Text(greet)
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
