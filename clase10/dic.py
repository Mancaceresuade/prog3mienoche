def backtrack(letters, dictionary, path, results):
    if not letters:
        results.append(path[:])
        return
    
    for word in dictionary:
        temp_letters = letters[:]
        can_use = True
        for ch in word:
            if ch in temp_letters:
                temp_letters.remove(ch)
            else:
                can_use = False
                break
        if can_use:
            path.append(word)
            backtrack(temp_letters, dictionary, path, results)
            path.pop()  # backtrack

# Ejemplo
letters = ['a','b','c','d']
dictionary = ["ab", "abc", "ad", "cd", "bcd", "d"]
results = []

backtrack(letters, dictionary, [], results)
print(results)