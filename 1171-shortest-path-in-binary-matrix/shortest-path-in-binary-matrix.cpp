class Solution {
public:
    int shortestPathBinaryMatrix(vector<vector<int>>& grid) {
            int n = grid.size();

        if (grid[0][0] == 1 || grid[n-1][n-1] == 1)
            return -1;

        queue<pair<int,int>> q;
        q.push({0,0});

        vector<vector<int>> dist(n, vector<int>(n, -1));
        dist[0][0] = 1;

        int dx[8] = {-1,-1,-1,0,0,1,1,1};
        int dy[8] = {-1,0,1,-1,1,-1,0,1};

        while(!q.empty()) {
            auto [x,y] = q.front();
            q.pop();

            if(x == n-1 && y == n-1)
                return dist[x][y];

            for(int k=0; k<8; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if(nx>=0 && ny>=0 && nx<n && ny<n &&
                   grid[nx][ny]==0 && dist[nx][ny]==-1) {

                    dist[nx][ny] = dist[x][y] + 1;
                    q.push({nx,ny});
                }
            }
        }

        return -1;


    }
};