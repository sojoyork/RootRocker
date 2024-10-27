import java.io.BufferedReader
import java.io.InputStreamReader

fun runCommand(command: String): String {
    val processBuilder = ProcessBuilder(command.split(" "))
    val process = processBuilder.start()
    val reader = BufferedReader(InputStreamReader(process.inputStream))
    val output = StringBuilder()
    var line: String? = reader.readLine()

    while (line != null) {
        output.append(line).append("\n")
        line = reader.readLine()
    }

    return output.toString()
}

fun main() {
    println("###################################################")
    println("### Welcome to RootRocker! 🎸                  ###")
    println("### Let's start rocking!                        ###")
    println("###################################################")

    // Check connected devices
    val deviceList = runCommand("adb devices")
    println("Connected Devices: \n$deviceList")

    if (deviceList.contains("device")) {
        println("Device detected. Proceeding with rooting...")

        // Example: Reboot the device to bootloader (to flash custom recovery)
        val rebootOutput = runCommand("adb reboot bootloader")
        println("Rebooting to bootloader: \n$rebootOutput")

        // Here you would add commands to install TWRP, root binaries, etc.
    } else {
        println("No device connected. Please connect a device via USB.")
    }
}
