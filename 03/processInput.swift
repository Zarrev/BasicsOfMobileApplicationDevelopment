import Foundation

extension Array where Element == Int{
    func find(includedElement: (Element) -> Bool) -> [Element] {
        var indexList = [Element]()
		for (idx, element) in self.enumerated() {
            if includedElement(element) {
                indexList.append(idx)
            }
        }
		return indexList
    }
}

extension Array where Element == Array<Int> {
	func extendFilter(yourFilterFunc: (Int) -> Bool) -> [Element] {
		var result = [Element]()
		for element in self {
            result.append(element.filter {yourFilterFunc($0)})
        }
		
		return result
	}
} 

func stringToNumberArray(string: String) -> [Int]? {
	var array = [Int]()
	var intStr: String = ""
	for char in string {
	
		if "0"..."9" ~= char {
			intStr = intStr + String(char)
		}
		else {
			if let realInt = Int(intStr) {
				array.append(realInt)
				intStr = ""
			}
		}
	}
	if let realInt = Int(intStr) {
		array.append(realInt)
		intStr = ""
	}
	
	if array.count > 0 {
		return array
	}
	
	return nil
}

func readFromConsoleUntilXChar(arrayOfArrays: inout [[Int]], strToNumArrFunc: (String) -> [Int]?) {
	print("""
	The program read only the numbers line by line into an array of arrays.
	If you would like to stop the number importation then give an 'x' as input!
	The program convert the negative numbers to positive.
	For example: -58 -> 58
	""")
	while let response = readLine(){
		if response == "x" {
			break
		}
		
		if let array = strToNumArrFunc(response) {
			arrayOfArrays.append(array);
		}
	}
}

func readFromFile(arrayOfArrays: inout [[Int]], strToNumArrFunc: @escaping (String) -> [Int]?) {
	print("""
	The program search the file in the bundle!
	The program read only the numbers line by line into an array of arrays.
	The program convert the negative numbers to positive.
	For example: -58 -> 58
	""")
	if let file = readLine() {
		if let path = Bundle.main.path(forResource: file, ofType: "txt") {
			do {
				var temporary = arrayOfArrays
				let contents = try String(contentsOfFile: path, encoding: .utf8)
				print(contents)
				contents.enumerateLines{
					line, _ in
					if let array = strToNumArrFunc(line) {
						temporary.append(array);
					}
				}
				arrayOfArrays = temporary
			}
			catch {
				print("Unexpected error: \(error).")
			}
		}
		else {
			print("The file was not found!")
		}
	}
}

func getMaxFromArrayOfArrays(arrayOfArrays: [[Int]]) -> Int? {
	if arrayOfArrays.count > 0 {
		var max: Int = arrayOfArrays[0].max()!
		for array in arrayOfArrays {
			if max < array.max()! {
				max = array.max()!
			}
		}
		return max
	}
	
	return nil
}

func setMinInArrayOfArrays(arrayOfArrays: inout [[Int]]) {
	if arrayOfArrays.count > 0 {
		var min: Int = arrayOfArrays[0].min()!
		for array in arrayOfArrays {
			if min > array.min()! {
				min = array.min()!
			}
		}
	
		for arrayIdx in 0..<arrayOfArrays.count {
			let indexList = arrayOfArrays[arrayIdx].find(includedElement: {$0 == min})
			for idx in indexList {
				arrayOfArrays[arrayIdx][idx] = 0
			}
		}
	}
}

func meansOfEverything(arrayOfArrays: [[Int]]) -> ([Double], Double) {
	var means = [Double]()
	var meansOfMean: Double = 0
	for array in arrayOfArrays {
		var oneArrayMean: Double = 0
		for element in array{
			oneArrayMean = oneArrayMean + Double(element)
		}
		means.append(oneArrayMean/Double(array.count))
	}
	
	for element in means {
		meansOfMean = meansOfMean + element
	}
	meansOfMean = meansOfMean/Double(means.count)
	
	return (means, meansOfMean)
}

func odd(number: Int) -> Bool {
	return number%2 != 0
}

func even(number: Int) -> Bool {
	return number%2 == 0
}

func prime(number: Int) -> Bool {
	return number > 1 && !(2...(Int(sqrt(Double(number)))+1)).contains { number%$0 == 0 }
}

func printMenu() {
print("""
Select an option!
1.) Read data from console
2.) Read data from file
3.) Set the scanned data's minimum value to zero
4.) Get the maximum value of the array of arrays
5.) Get means for each array and overall
6.) Filter: Show only the odd values
7.) Filter: Show only the even values
8.) Filter: Show only the prime numbers
9.) Filter: Show those numbers which are divisable by five
0.) Print my data representation
+.) Reinit
X.) Exit
""")
}
 

var aoa = [[Int]]()
printMenu()
while let response = readLine(){
	switch response.lowercased() {
		case "1":
			readFromConsoleUntilXChar(arrayOfArrays: &aoa, strToNumArrFunc: stringToNumberArray)
			print(aoa)
		case "2":
			readFromFile(arrayOfArrays: &aoa, strToNumArrFunc: stringToNumberArray)
			print(aoa)
		case "3":
			setMinInArrayOfArrays(arrayOfArrays: &aoa)
			print("Set minimum to 0: \(aoa)")
		case "4":
			if let max = getMaxFromArrayOfArrays(arrayOfArrays: aoa) {
				print("Maximum value is: \(max)")
			}
			else {
				print("Maximum value is: Not exist")
			}
		case "5":
			print("Means of arrays and overall: \(meansOfEverything(arrayOfArrays: aoa))")
		case "6":
			print("Odd filter: ", aoa.extendFilter(yourFilterFunc: odd))
		case "7":
			print("Even filter: ", aoa.extendFilter(yourFilterFunc: even))
		case "8":
			print("Prime filter: ", aoa.extendFilter(yourFilterFunc: prime))
		case "9":
			print("Divisable numbers by 5: ", aoa.extendFilter {$0%5 == 0})
		case "0":
			print(aoa)
		case "+":
			aoa = [[Int]]()
			print("Array was reinitialized")
		case "x":
			print("The program is shutting down...")
		default:
			print("You misspelled something! Try again.")
	}
	if response.lowercased() == "x" {
		break
	}
	print("\n")
	printMenu()
}
