def permutaciones(nums):
    resultado = []

    def dfs(path, used):
        
        if len(path) == len(nums):
            resultado.append(path.copy())
            return

        for i in range(len(nums)):
            if not used[i]:
                
                path.append(nums[i])
                used[i] = 1

                # Llamada recursiva
                dfs(path, used)

                # Backtracking: deshacemos la elección
                path.pop()
                used[i] = 0

    dfs([], [0]*len(nums))
    return resultado

nums = [1, 2, 3]
print(permutaciones(nums))