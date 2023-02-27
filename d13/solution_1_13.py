with open("data.txt") as file:
    data = file.readlines()

list_data = []
for line in data:
    packet = []
    if not line.isspace():
        exec(f"packet = {line.strip()}")
        list_data.append(packet)

print(*list_data, sep="\n\n\n")
