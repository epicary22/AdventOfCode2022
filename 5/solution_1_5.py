with open("data.txt") as data_file:
    data = data_file.readlines()
    crates_txt = data[:8]
    instructions = data[10:]

crates = []

for stack in range(9):
    crates.append([])

for row in crates_txt:
    for column in range(9):
        char_index = column*4 + 1
        crates[column].insert(0, row[char_index])

crates = [[crate for crate in stack if crate != " "] for stack in crates]

def print_crates() -> None:
    for stack in range(9):
        print(*crates[stack], end="\n")

def execute_instruction(instruction: str) -> None:
    # Instruction: move x from y to z
    slice_height, from_stack_num, to_stack_num = [int(num) for num in instruction.split(" ")[1::2]]
    from_stack = crates[from_stack_num - 1]
    to_stack = crates[to_stack_num - 1]
    slice_start = len(from_stack) - slice_height

    slice_stack = from_stack[slice_start:][::-1]
    to_stack.extend(slice_stack)
    crates[from_stack_num - 1] = from_stack[:slice_start]

for line in instructions:
    execute_instruction(line)

print_crates()

print("Final message: ", *[stack[-1] for stack in crates], sep="")

