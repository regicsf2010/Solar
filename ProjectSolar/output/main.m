clear; clc; close all;

nFiles = length(dir('data'))-2;

for i = 0 : nFiles-1
    in = load(['data/e' num2str(i) '.m']);
    x(i+1, :) = in(1, :);
end
figure
set(gcf, 'Visible', 'on')
hold on, box on, grid on

%h1 = shadedErrorBar(1:length(x(1, :)), x(1, :), x(2, :), {'-k', 'Linewidth', 2});
h = line(1:length(x(1, :)), x, 'Linewidth', 2);
    
title('Griewank [f(0) = 0]', 'fontsize', 14) % altera por função 
%legend(h, 'Média de fitness')
xlabel('Gerações', 'fontsize', 14)
ylabel('Média de Fitness', 'fontsize', 14)
xlim([0 length(x(1, :))])
set(gca, 'fontsize', 14)
set(gca, 'LooseInset', get(gca, 'TightInset'))
print('-depsc', 'Griewank'); % altera por função

for i = 0 : nFiles-1
    in = load(['data/e' num2str(i) '.m']);
    y(i+1) = min(in(3, :));
end

disp(y)



